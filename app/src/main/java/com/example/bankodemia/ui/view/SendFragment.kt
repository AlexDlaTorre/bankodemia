package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.ui.viewModel.SendViewModel
import com.example.bankodemia.databinding.FragmentSendBinding

class SendFragment : Fragment() {

    private var _binding: FragmentSendBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sendViewModel =
            ViewModelProvider(this).get(SendViewModel::class.java)

        _binding = FragmentSendBinding.inflate(inflater, container, false)


        binding.homeDetailBtnBackToHome.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_sendFragment_to_navigation_home)
        }
        binding.sendBtnAdd.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_sendFragment_to_addContactFragment)
        }

        binding.sendIvLogo.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_sendFragment_to_transferDetailFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}