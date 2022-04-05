package com.example.bankodemia.ui.view.deposit

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.core.once
import com.example.bankodemia.core.showSnackBarMessage
import com.example.bankodemia.core.showToastMessage
import com.example.bankodemia.core.types.MovementType
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.core.utils.general
import com.example.bankodemia.core.utils.selfDeposit
import com.example.bankodemia.core.zero
import com.example.bankodemia.databinding.FragmentDepositBinding
import com.example.bankodemia.domain.domainObjects.Transaction.TransactionDTO
import com.example.bankodemia.domain.domainObjects.Transaction.TransactionPostReponseDTO
import com.example.bankodemia.ui.home.HomeFragment
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat

class DepositFragment : Fragment() {

    private var _binding: FragmentDepositBinding? = null
    private val binding get() = _binding!!
    private var money: Int = Int.zero
    private var index: Int = Int.zero
    private lateinit var communicator: FragmentCommunicator
    private lateinit var viewModel: DepositViewModel
    private val initialCountDown: Long = 5000
    private val countInterval: Long = 1000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepositBinding.inflate(inflater, container, false)
        communicator = requireActivity() as FragmentCommunicator
        viewModel = ViewModelProvider(this).get(DepositViewModel::class.java)
        showAlert()
        setupEvents()
        setupObservers()
        return binding.root
    }

    private fun setupObservers() {
        viewModel.uiStateEmitter.observe(viewLifecycleOwner) { updateUI(it) }
    }

    fun updateUI(uiState: BaseUiState) {
        when (uiState) {
            is BaseUiState.SuccessResult<*> -> {
                if (uiState.result is TransactionPostReponseDTO) {
                    communicator.showLoader(false)
                    val transaction = uiState.result as TransactionPostReponseDTO
                    backToHome()
                    communicator.showToastMessage(getString(R.string.successDeposit, transaction.data.transaction.amount.toString()))
                }
            }
            is BaseUiState.loading -> {
                communicator.showLoader(true)
            }
            is BaseUiState.Error -> {
                communicator.showLoader(false)
                showSnackBarMessage(uiState.message ?: general, Snackbar.LENGTH_LONG)
            }
        }
    }

    private fun showAlert() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.welcome_message)
        builder.setMessage(R.string.deposit_description)
        builder.apply {
            setPositiveButton(R.string.begin) { _, _ ->
                startGame()
            }
            setNegativeButton(R.string.cancel) { _, _ ->
                view?.findNavController()?.navigate(R.id.action_depositFragment_to_navigation_home)
            }
        }
        builder.create().show()
    }

    private fun startGame() {
        val timer = object : CountDownTimer(initialCountDown, countInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished/1000
                binding.counterLabel.text = timeLeft.toString()
            }
            override fun onFinish() {
                endGame()
            }
        }
        timer.start()
    }

    private fun endGame() {
        binding.increaseButton.isEnabled = false
        binding.depositButton.visibility = View.VISIBLE
    }

    private fun setupEvents() {
        binding.increaseButton.setOnClickListener {
            increaseMoney()
        }
        binding.depositButton.setOnClickListener {
            viewModel.makeDeposit(money, MovementType.DEPOSIT, selfDeposit)
        }
        binding.backButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_depositFragment_to_navigation_home)
        }
    }

    private fun backToHome() {
        view?.findNavController()?.navigate(R.id.action_depositFragment_to_navigation_home)
    }

    private fun increaseMoney(){
        if (money == Int.zero) {
            money += Int.once
        } else {
            val sum = index + money
            index = money
            money = sum
            showMoney(money)
        }
    }

    fun showMoney(money: Int) {
        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.maximumFractionDigits = Int.zero
        binding.amountTextView.text = numberFormat.format(money.toDouble())
    }
}