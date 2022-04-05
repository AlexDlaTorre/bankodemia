package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.databinding.FragmentProcessingTransactionBinding
import com.example.bankodemia.ui.home.HomeFragment


class ProcessingTransactionFragment : Fragment() {
    private var _binding: FragmentProcessingTransactionBinding? = null
    private val binding get() = _binding!!
    private lateinit var communicator: FragmentCommunicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProcessingTransactionBinding.inflate(inflater, container, false)
        communicator = requireActivity() as FragmentCommunicator
        setupEvents()
        return binding.root
    }

    private fun setupEvents() {
        binding.buttonTransferDone.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_processingTransactionFragment_to_navigation_home)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}