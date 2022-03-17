package com.example.bankodemia.ui.view.deposit

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.core.once
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.core.zero
import com.example.bankodemia.databinding.FragmentDepositBinding
import com.example.bankodemia.ui.home.HomeFragment
import java.text.NumberFormat

class DepositFragment : Fragment() {

    private var _binding: FragmentDepositBinding? = null
    private val binding get() = _binding!!
    private var money: Int = Int.zero
    private var index: Int = Int.zero
    private lateinit var communicator: FragmentCommunicator
    private lateinit var countDown : CountDownTimer
    private val initialCountDown: Long = 5000
    private val countInterval: Long = 1000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepositBinding.inflate(inflater, container, false)
        communicator = requireActivity() as FragmentCommunicator
        setupView()
        setupObservers()
        return binding.root
    }

    private fun setupView() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.welcome_message)
        builder.setMessage(R.string.deposit_description)
        builder.apply {
            setPositiveButton(R.string.begin) { _, _ ->
                startGame()
            }
            setNegativeButton(R.string.cancel) { _, _ ->
                communicator.goTo(HomeFragment())
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

    private fun setupObservers() {
        binding.increaseButton.setOnClickListener {
            increaseMoney()
        }
        binding.depositButton.setOnClickListener {
            // TODO - implementar flujo de deposito
        }
        binding.backButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_depositFragment_to_navigation_home)
        }
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