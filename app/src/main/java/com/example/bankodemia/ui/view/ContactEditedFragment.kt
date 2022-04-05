package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bankodemia.R
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.databinding.FragmentContactAddedBinding
import com.example.bankodemia.databinding.FragmentContactEditedBinding
import com.example.bankodemia.ui.view.transaction.SendFragment


class ContactEditedFragment : Fragment() {

    private var _binding: FragmentContactEditedBinding? = null
    private lateinit var communicator: FragmentCommunicator
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactEditedBinding.inflate(inflater, container, false)
        communicator = requireActivity() as FragmentCommunicator

        setEvents()
        return binding.root
    }

    fun setEvents() {
        binding.contacEditedBtnBackToSend.setOnClickListener { view: View ->
            communicator.goTo(SendFragment())
        }

        fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
}