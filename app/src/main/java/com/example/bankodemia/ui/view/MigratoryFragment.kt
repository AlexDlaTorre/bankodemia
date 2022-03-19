package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.databinding.FragmentIneBinding
import com.example.bankodemia.databinding.FragmentMigratoryBinding


class MigratoryFragment : Fragment() {
    private var _binding: FragmentMigratoryBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMigratoryBinding.inflate(inflater, container, false)
        initializeComponents()
        return mBinding.root
    }

    private fun initializeComponents() {
        with(mBinding) {
            migratoryBtnUploadInfo.setOnClickListener {
                findNavController().navigate(R.id.action_migratoryFragment_to_passwordFragment)
            }

            migratoryBtnBackToIdentityMenu.setOnClickListener {
                findNavController().navigate(R.id.action_migratoryFragment_to_identityMenuFragment)
            }
        }
    }
}