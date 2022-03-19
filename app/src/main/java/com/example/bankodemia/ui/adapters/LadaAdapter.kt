package com.example.bankodemia.UI.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankodemia.R
import com.example.bankodemia.UI.view.OnClikListener
import com.example.bankodemia.data.model.Lada
import com.example.bankodemia.databinding.ItemLadaBinding

class LadaAdapter(private val ladas: List<Lada>, private var listener: OnClikListener) :
    RecyclerView.Adapter<LadaAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_lada, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lada = ladas[position]

        with(holder) {
            setListener(lada, position)
            mBinding.ladaTvLadaNumber.text = lada.lada
            mBinding.ladaTvCountry.text = lada.name
        }
    }

    override fun getItemCount(): Int = ladas.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mBinding = ItemLadaBinding.bind(view)

        fun setListener(lada: Lada, position: Int) {
            mBinding.root.setOnClickListener {
                listener.onClick(lada, position)
            }
        }

    }
}