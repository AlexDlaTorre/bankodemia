package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.cottacush.android.currencyedittext.CurrencyInputWatcher
import com.example.bankodemia.R
import com.example.bankodemia.core.Extensions.alert
import com.example.bankodemia.core.Extensions.negativeButton
import com.example.bankodemia.core.Extensions.positiveButton
import com.example.bankodemia.core.activateButton
import com.example.bankodemia.core.empty
import com.example.bankodemia.core.hideKeyboard
import com.example.bankodemia.core.showSnackBarMessage
import com.example.bankodemia.core.types.MovementType
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.core.utils.CONTACTDATA
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.core.utils.general
import com.example.bankodemia.databinding.FragmentTransferDetailBinding
import com.example.bankodemia.domain.domainObjects.Contact.ContactDTO
import com.example.bankodemia.domain.domainObjects.Transaction.TransactionPostReponseDTO
import com.example.bankodemia.ui.view.transaction.SendFragment
import com.example.bankodemia.ui.viewModel.TransferDetailViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_transfer_detail.*
import java.util.*

class TransferDetailFragment : Fragment(), Fields {
    private var _binding: FragmentTransferDetailBinding? = null
    private val binding get() = _binding!!
    private var contact: ContactDTO? = null
    private lateinit var communicator: FragmentCommunicator
    private lateinit var viewModel: TransferDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransferDetailBinding.inflate(inflater, container, false)
        contact = arguments?.getSerializable(CONTACTDATA) as ContactDTO
        communicator = requireActivity() as FragmentCommunicator
        viewModel = ViewModelProvider(this).get(TransferDetailViewModel::class.java)
        setupView()
        setupEvents()
        setupObservers()
        validationFields()
        return binding.root
    }

    private fun setupObservers() {
        viewModel.uiStateEmitter.observe(viewLifecycleOwner) { updateUI(it)}
    }

    private fun updateUI(uiState: BaseUiState) {
        when (uiState) {
            is BaseUiState.SuccessResult<*> -> {
                if (uiState.result is TransactionPostReponseDTO) {
                    showSuccessView()
                    communicator.showLoader(false)
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

    private fun showSuccessView() {
        communicator.goTo(ProcessingTransactionFragment())
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
            val activity = activity ?: return@setOnClickListener
            activity.alert {
                setTitle(getString(R.string.confirm_transfer))
                setMessage(getString(R.string.confirm_transfer_description))
                positiveButton { var concept = getString(R.string.default_concept)
                    if (!transferTietConcept.text.toString().isEmpty()) { concept = transferTietConcept.text.toString() }
                    viewModel.makePayment(transferTilAmount.getNumericValue().toInt(),
                        MovementType.PAYMEMT,
                        contact?.user?.id ?: String.empty,
                        concept) }
                negativeButton {}
            }
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