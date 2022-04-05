package com.example.bankodemia.ui.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.core.utils.TRANSACTIONDETAIL
import com.example.bankodemia.databinding.FragmentHomeDetailBinding
import com.example.bankodemia.domain.domainObjects.Transaction.TransactionDTO
import com.example.bankodemia.domain.domainObjects.User.UserDTO
import com.example.bankodemia.ui.home.HomeFragment

class HomeDetailFragment : Fragment() {

    private var _binding: FragmentHomeDetailBinding? = null
    private val binding get() = _binding!!
    private var transactionDetail: TransactionDTO? = null
    private lateinit var communicator: FragmentCommunicator

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeDetailBinding.inflate(inflater, container, false)
        transactionDetail = arguments?.getSerializable(TRANSACTIONDETAIL) as? TransactionDTO
        communicator = requireActivity() as FragmentCommunicator
        setupEvents()
        setupView()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setupView() {
        val transactionDetail = transactionDetail?.let { it } ?: return
        binding.apply {
            homeDetailtvTransactionAmount.text = transactionDetail.formattedAmount
            homeDetailTvTransaction.text = transactionDetail.concept
            homeDetailTvTransactionIdNum.text = transactionDetail.id
            homeDetailTvConceptDetail.text = transactionDetail.concept
            homeDetailTvDateTime.text = transactionDetail.formattedDate
        }
    }

    private fun setupEvents() {
        binding.homeDetailBtnBackToHome.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeDetailFragment_to_navigation_home)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}