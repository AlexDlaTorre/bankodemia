package com.example.bankodemia.ui.view.deposit

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bankodemia.R
import com.example.bankodemia.core.once
import com.example.bankodemia.core.zero
import com.example.bankodemia.databinding.FragmentDepositBinding
import java.text.NumberFormat

class DepositFragment : Fragment() {

    private var _binding: FragmentDepositBinding? = null
    private val binding get() = _binding!!
    private var money: Int = Int.zero
    private var index: Int = Int.zero

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepositBinding.inflate(inflater, container, false)
        setupObservers()
        return binding.root
    }

    fun setupView() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.welcome_message)
        builder.setMessage(R.string.deposit_description)
        builder.apply {
            setPositiveButton(R.string.begin,
            DialogInterface.OnClickListener { _, _ ->

            })
            setNegativeButton(R.string.cancel) { _, _ ->

            }
        }
        val dialog = builder.create().show()
    }

    fun setupObservers() {
        binding.increaseButton.setOnClickListener {
            increaseMoney()
        }
    }

    fun increaseMoney(){
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