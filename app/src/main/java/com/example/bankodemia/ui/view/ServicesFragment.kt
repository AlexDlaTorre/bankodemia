package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankodemia.core.utils.ParseJson
import com.example.bankodemia.data.model.Service
import com.example.bankodemia.databinding.FragmentServicesBinding
import com.example.bankodemia.ui.adapters.ServicesAdapter
import com.google.gson.Gson


class ServicesFragment : Fragment() {
    private var _binding: FragmentServicesBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var mLinearLayoutManager: RecyclerView.LayoutManager
    private lateinit var mServiceAdapter: ServicesAdapter

    private var listService: List<Service>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServicesBinding.inflate(inflater, container, false)
        setRecyclerViewServices()
        return mBinding.root
    }

    private fun setRecyclerViewServices() {
        val json = context?.let { ParseJson().getJsonDataFromAsset(it, "servicios_terceros.json") }
        listService = Gson().fromJson(json,Array<Service>::class.java).toList()

        if (listService != null) {
            mServiceAdapter = ServicesAdapter(listService!!)
            mLinearLayoutManager = LinearLayoutManager(activity?.applicationContext)

            mBinding.servicesRvServices.apply {
                setHasFixedSize(true)
                adapter = mServiceAdapter
                layoutManager = mLinearLayoutManager
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}