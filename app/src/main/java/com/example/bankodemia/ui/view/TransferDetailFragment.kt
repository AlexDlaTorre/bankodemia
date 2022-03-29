package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import com.cottacush.android.currencyedittext.CurrencyInputWatcher
import com.example.bankodemia.R
import com.example.bankodemia.core.activateButton
import com.example.bankodemia.core.hideKeyboard
import com.example.bankodemia.core.utils.CONTACTDATA
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.databinding.FragmentTransferDetailBinding
import com.example.bankodemia.domain.domainObjects.Contact.ContactDTO
import com.example.bankodemia.ui.view.transaction.SendFragment
import java.util.*

class TransferDetailFragment : Fragment(), Fields {
    private var _binding: FragmentTransferDetailBinding? = null
    private val binding get() = _binding!!
    private var contact: ContactDTO? = null
    private lateinit var communicator: FragmentCommunicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransferDetailBinding.inflate(inflater, container, false)
        contact = arguments?.getSerializable(CONTACTDATA) as ContactDTO
        communicator = requireActivity() as FragmentCommunicator
        setupView()
        setupEvents()
        validationFields()
        return binding.root
    }

    private fun setupView() {
        val contact = contact ?: return
        binding.apply {
            transfertvTransferContact.text = contact.shortName
            transferTvTransferAccount.text = contact.user.id
        }
    }

    private fun setupEvents() {
        binding.transferBtnBackToSend.setOnClickListener { view : View ->
            communicator.goTo(SendFragment())
        }
        binding.transferBtnMakeTransfer.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_transferDetailFragment_to_processingTransactionFragment)
        }
        binding.transferContainerView.setOnClickListener {
            hideKeyboard()
        }
    }

    override fun validationFields() {
        with(binding) {
            transferTilAmount.addTextChangedListener { amount ->
                var isValid = true
                val text = amount.toString()
                if (text == "$" || text == "$ " || text == "$ 0") {
                    isValid = false
                    transferTilAmount.error = getString(R.string.invalid_amount)
                } else {
                    transferTilAmount.error = null
                }
                activateButton(
                    fragment = this@TransferDetailFragment,
                    transferBtnMakeTransfer,
                    isValid
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}