package com.example.bankodemia.UI.View.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.databinding.FragmentHomeDetailBinding
import com.example.bankodemia.viewModel.home.HomeDetailViewModel


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
        val root: View = binding.root

        val homeBtnSendBack:Button = binding.homeDetailBtnBackToHome

        homeBtnSendBack.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeDetailFragment_to_navigation_home)
        }
        return root
    }
}