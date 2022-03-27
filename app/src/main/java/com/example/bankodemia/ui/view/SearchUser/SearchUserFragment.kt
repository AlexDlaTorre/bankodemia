package com.example.bankodemia.ui.view.SearchUser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankodemia.R
import com.example.bankodemia.core.empty
import com.example.bankodemia.core.showSnackBarMessage
import com.example.bankodemia.core.twice
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.core.utils.general
import com.example.bankodemia.data.model.User
import com.example.bankodemia.databinding.FragmentSearchUserBinding
import com.example.bankodemia.domain.domainObjects.User.UserDTO
import com.example.bankodemia.domain.domainObjects.User.getUsers.UserGetResponseDTO
import com.example.bankodemia.ui.home.AdapterItemSelected
import com.example.bankodemia.ui.view.AddContactFragment
import com.example.bankodemia.ui.view.HomeDetailFragment
import com.example.bankodemia.ui.viewModel.SearchUserViewModel
import com.google.android.material.snackbar.Snackbar

class SearchUserFragment : Fragment(), AdapterItemSelected {

    private var _binding: FragmentSearchUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SearchUserViewModel
    private var users: List<UserDTO> = mutableListOf()
    private lateinit var communicator: FragmentCommunicator
    private lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchUserBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SearchUserViewModel::class.java)
        communicator = requireActivity() as FragmentCommunicator
        setupEvents()
        setupObservers()
        setupReclycerView()
        return binding.root
    }

    private fun setupObservers() {
        viewModel.uiStateEmitter.observe(viewLifecycleOwner) { updateUI(it)}
    }

    private fun updateUI(uiState: BaseUiState) {
        when (uiState) {
            is BaseUiState.SuccessResult<*> -> {
                if (uiState.result is UserGetResponseDTO) {
                    binding.loadingView.visibility = View.GONE
                    val users = uiState.result as UserGetResponseDTO
                    this.users = users.data.users
                    updateListInfo()
                }
            }
            is BaseUiState.Error -> {
                binding.loadingView.visibility = View.GONE
                showSnackBarMessage(uiState.message ?: general, Snackbar.LENGTH_LONG)
            }
            is BaseUiState.loading -> {
                binding.loadingView.visibility = View.VISIBLE
            }
        }
    }

    fun setupReclycerView() {
        val activity = activity ?: return
        adapter = UsersAdapter(users, this)
        binding.apply {
            userReclyclerView.layoutManager = LinearLayoutManager(activity)
            userReclyclerView.setHasFixedSize(true)
            userReclyclerView.adapter = adapter
        }
    }

    fun updateListInfo() {
        adapter.notifyDataSetChanged()
    }

    override fun <T> itemSelected(item: T) {
        communicator.sendData(item, AddContactFragment())
    }

    private fun setupEvents() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.isNullOrEmpty()) return false
                if (query.count() > Int.twice) {
                    viewModel.getUser(query)
                }
                return false
            }
        })
        binding.backToTransfersButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_searchUserFragment_to_sendFragment)
        }
    }
}