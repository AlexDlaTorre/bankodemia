package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.core.instances.SharedPreferencesInstance
import com.example.bankodemia.core.types.IdentityType
import com.example.bankodemia.databinding.FragmentIdentityMenuBinding

class IdentityMenuFragment : Fragment() {
    private var _binding: FragmentIdentityMenuBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIdentityMenuBinding.inflate(inflater, container, false)
        initializeComponents()
        return mBinding.root
    }

    private fun initializeComponents() {
        with(mBinding) {
            identityMenuBtnBackToIdentity.setOnClickListener {
                findNavController().navigate(R.id.action_identityMenuFragment_to_identityFragment)
            }

            identityMenuBtnIne.setOnClickListener {
                SharedPreferencesInstance.setStringValue(getString(R.string.saved_document),IdentityType.INE.value)
                findNavController().navigate(R.id.action_identityMenuFragment_to_documentFragment)
            }

            identityMenuBtnMigratory.setOnClickListener {
                SharedPreferencesInstance.setStringValue(getString(R.string.saved_document),IdentityType.MIGRATION_FORM.value)
                findNavController().navigate(R.id.action_identityMenuFragment_to_documentFragment)
            }

            identityMenuBtnPassport.setOnClickListener {
                SharedPreferencesInstance.setStringValue(getString(R.string.saved_document),IdentityType.PASSPORT.value)
                findNavController().navigate(R.id.action_identityMenuFragment_to_documentFragment)
            }
        }
    }
}