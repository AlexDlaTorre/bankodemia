package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.databinding.FragmentContactAddedBinding

class ContactAddedFragment : Fragment() {

    private var _binding: FragmentContactAddedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactAddedBinding.inflate(inflater, container, false)

        binding.contacAddedBtnBackToHome.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_contactAddedFragment_to_navigation_home)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}