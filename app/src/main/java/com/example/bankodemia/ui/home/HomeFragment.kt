package com.example.bankodemia.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankodemia.ui.viewModel.HomeViewModel
import com.example.bankodemia.core.showToastMessage
import com.example.bankodemia.core.utils.BaseUiState
import androidx.navigation.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.databinding.FragmentHomeBinding
import com.example.bankodemia.domain.domainObjects.User.geUserProfile.UserProfileDTO
import com.example.bankodemia.domain.domainObjects.Transaction.TransactionDTO
import com.example.bankodemia.ui.view.HomeDetailFragment

class HomeFragment : Fragment(), AdapterItemSelected {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var communicator: FragmentCommunicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        communicator = requireActivity() as FragmentCommunicator
        viewModel.getUserProfileData()
        setupObservers()
        setupEvents()
        return binding.root
    }

    fun setupEvents() {
        binding.homeBtnSend.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_navigation_home_to_sendFragment)
        }
        binding.homeBtnReceive.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.homeFragment_to_depositFragment)
        }
    }

    private fun setupObservers() {
        viewModel.uiStateEmitter.observe(viewLifecycleOwner) { updateUI(it) }
    }

    private fun updateUI(uiState: BaseUiState) {
        when (uiState) {
            is BaseUiState.SuccessResult<*> -> {
                if (uiState.result is UserProfileDTO) {
                    val userProfileInfo = uiState.result as UserProfileDTO
                    setupReclycerView(userProfileInfo.data.transactions, false)
                    setupView(userProfileInfo)
                }
            }
            is BaseUiState.Error -> {
                showToastMessage(uiState.error.localizedMessage, Toast.LENGTH_SHORT)
            }
            is BaseUiState.loading -> {
                setupReclycerView(mutableListOf(), true)
            }
        }
    }

    fun setupView(user: UserProfileDTO) {
        binding.apply {
            balanceShimmer.stopShimmer()
            balanceShimmer.visibility = View.GONE
            hometvAvailableMoneyAmount.text = user.data.formattedBalance
        }
    }

    fun setupReclycerView(userTansactions: List<TransactionDTO>, isSkeleton: Boolean) {
        val activity = activity ?: return
        val adapter = TransactionsAdapter(userTansactions, isSkeleton, this)
        binding.apply {
            homeRvTransactions.layoutManager = LinearLayoutManager(activity)
            homeRvTransactions.setHasFixedSize(true)
            homeRvTransactions.adapter = adapter
        }
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun <T> itemSelected(item: T) {
        communicator.sendData(item, HomeDetailFragment())
    }
}