package com.example.bankodemia.ui.view.SearchUser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.bankodemia.R
import com.example.bankodemia.databinding.FragmentSearchUserBinding
import com.example.bankodemia.ui.viewModel.SearchUserViewModel

class SearchUserFragment : Fragment() {

    private var _binding: FragmentSearchUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SearchUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchUserBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SearchUserViewModel::class.java)
        setupEvents()
        return binding.root
    }

    private fun setupEvents() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                binding.searchView.clearFocus()
                
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

        })
    }
}