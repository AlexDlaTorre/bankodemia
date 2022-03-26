package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.databinding.FragmentIneBinding


class IneFragment : Fragment() {
    private var _binding: FragmentIneBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIneBinding.inflate(inflater, container, false)
        initializeComponents()
        return mBinding.root
    }

    private fun initializeComponents() {
        with(mBinding) {
            ineBtnUploadInfo.setOnClickListener {
                findNavController().navigate(R.id.action_ineFragment_to_passwordFragment)
            }

            ineBtnBackToIdentityMenu.setOnClickListener {
                findNavController().navigate(R.id.action_ineFragment_to_identityMenuFragment)
            }
        }
    }
}