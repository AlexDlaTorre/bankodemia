package com.example.bankodemia.ui.view.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.core.showToastMessage
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.ui.viewModel.SendViewModel
import com.example.bankodemia.databinding.FragmentSendBinding
import com.example.bankodemia.domain.domainObjects.User.UserDTO
import com.example.bankodemia.domain.domainObjects.User.getUsers.UserGetResponseDTO

class SendFragment : Fragment() {

    private var _binding: FragmentSendBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SendViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         viewModel = ViewModelProvider(this).get(SendViewModel::class.java)
        _binding = FragmentSendBinding.inflate(inflater, container, false)

        setupEvents()

        return binding.root
    }

    private fun setupEvents() {
        binding.homeDetailBtnBackToHome.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_sendFragment_to_navigation_home)
        }
        binding.sendBtnAdd.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_sendFragment_to_searchUserFragment)
        }
        binding.sendIvLogo.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_sendFragment_to_transferDetailFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}