package com.example.bankodemia.UI.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bankodemia.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var _binding : FragmentMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}