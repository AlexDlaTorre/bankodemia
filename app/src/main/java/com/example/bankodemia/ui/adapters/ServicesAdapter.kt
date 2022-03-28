package com.example.bankodemia.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankodemia.R
import com.example.bankodemia.data.model.Service
import com.example.bankodemia.databinding.ItemCardviewServicesBinding

class ServicesAdapter(private val services: List<Service>) : RecyclerView.Adapter<ServicesAdapter.ViewHolder>(){
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_cardview_services,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service = services[position]

        with(holder){
            mBinding.servicesTvNameService.text = service.nameService
            mBinding.servicesTvServices.text= service.providersExample
        }
    }

    override fun getItemCount(): Int = services.size

    inner class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        val mBinding = ItemCardviewServicesBinding.bind(view)
    }
}