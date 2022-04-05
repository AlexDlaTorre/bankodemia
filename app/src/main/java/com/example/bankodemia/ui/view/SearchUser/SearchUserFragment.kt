package com.example.bankodemia.ui.view.SearchUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankodemia.R
import com.example.bankodemia.core.showSnackBarMessage
import com.example.bankodemia.core.twice
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.core.utils.USERDATA
import com.example.bankodemia.core.utils.general
import com.example.bankodemia.databinding.FragmentSearchUserBinding
import com.example.bankodemia.domain.domainObjects.User.UserDTO
import com.example.bankodemia.domain.domainObjects.User.getUsers.UserGetResponseDTO
import com.example.bankodemia.ui.home.AdapterItemSelected
import com.example.bankodemia.ui.home.HomeFragment
import com.example.bankodemia.ui.view.AddContactFragment
import com.example.bankodemia.ui.view.transaction.SendFragment
import com.example.bankodemia.ui.viewModel.SearchUserViewModel
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable

class SearchUserFragment : Fragment(), AdapterItemSelected {

    private var _binding: FragmentSearchUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SearchUserViewModel
    private var users: List<UserDTO> = mutableListOf()
    private lateinit var communicator: FragmentCommunicator
    private lateinit var adapter: UsersAdapter
    private var userNotFound = false

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
        viewModel.uiStateEmitter.observe(viewLifecycleOwner) { updateUI(it) }
    }

    private fun updateUI(uiState: BaseUiState) {
        when (uiState) {
            is BaseUiState.SuccessResult<*> -> {
                if (uiState.result is UserGetResponseDTO) {
                    binding.loadingView.visibility = View.GONE
                    val users = uiState.result as UserGetResponseDTO
                    userNotFound = !users.data.users.isEmpty()
                    this.users = users.data.users
                    setupReclycerView()
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

    override fun <T> itemSelected(item: T) {
        val bundle = Bundle().apply {
            putSerializable(USERDATA, item as Serializable)
        }
        view?.findNavController()?.navigate(R.id.action_searchUserFragment_to_addContactFragment, bundle)
    }

    private fun setupEvents() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                if (!userNotFound) {
                    showSnackBarMessage(getString(R.string.user_not_found), Snackbar.LENGTH_SHORT)
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    users = mutableListOf()
                    setupReclycerView()
                    binding.searchView.clearFocus()
                } else {
                    if (query.count() > Int.twice) {
                        viewModel.getUser(query)
                    }
                }
                return false
            }
        })
        binding.backToTransfersButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_searchUserFragment_to_sendFragment)
        }
    }
}