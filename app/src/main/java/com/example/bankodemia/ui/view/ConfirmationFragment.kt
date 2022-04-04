package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.core.instances.SharedPreferencesInstance
import com.example.bankodemia.databinding.FragmentConfirmationBinding
import com.example.bankodemia.databinding.FragmentIdentityMenuBinding

class ConfirmationFragment : Fragment() {
    private var _binding: FragmentConfirmationBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmationBinding.inflate(inflater, container, false)
        initializeComponents()
        return mBinding.root
    }

    private fun initializeComponents() {
        with(mBinding){
            confirmationBtnBackToMain.setOnClickListener {
                SharedPreferencesInstance.cleanFieldsCreateAccount(requireContext())
                findNavController().navigate(R.id.action_confirmationFragment_to_mainFragment)
            }
        }
    }
}