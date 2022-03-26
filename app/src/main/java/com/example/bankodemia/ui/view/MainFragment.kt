package com.example.bankodemia.UI.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.core.transitionFragment
import com.example.bankodemia.databinding.FragmentMainBinding
import com.example.bankodemia.ui.home.HomeFragment
import com.example.bankodemia.ui.view.CreateAccountFragment


class MainFragment : Fragment() {
    private var _binding : FragmentMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        initializeComponents()
        return mBinding.root
    }

    private fun initializeComponents() {
        with(mBinding) {
            mainBtnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
            }

            mainBtnCreateAccount.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_createAccountFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}