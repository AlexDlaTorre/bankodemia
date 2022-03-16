package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.ui.viewModel.HomeDetailViewModel
import com.example.bankodemia.databinding.FragmentHomeDetailBinding

class HomeDetailFragment : Fragment() {

    private var _binding: FragmentHomeDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeDetailViewModel =
            ViewModelProvider(this).get(HomeDetailViewModel::class.java)

        _binding = FragmentHomeDetailBinding.inflate(inflater, container, false)

        binding.homeDetailBtnBackToHome.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_homeDetailFragment_to_navigation_home)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}