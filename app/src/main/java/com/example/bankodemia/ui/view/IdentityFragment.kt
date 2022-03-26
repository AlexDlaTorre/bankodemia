package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.databinding.FragmentIdentityBinding

class IdentityFragment : Fragment() {
    private var _binding: FragmentIdentityBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIdentityBinding.inflate(inflater, container, false)
        initializeComponents()
        return mBinding.root
    }

    private fun initializeComponents() {
        with(mBinding){
            identityBtnBackToTelephone.setOnClickListener{
                findNavController().navigate(R.id.action_identityFragment_to_telephoneFragment)
            }

            identityBtnUnderstood.setOnClickListener {
                findNavController().navigate(R.id.action_identityFragment_to_identityMenuFragment)
            }
        }
    }

}