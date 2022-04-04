package com.example.bankodemia.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bankodemia.core.activateButton
import com.example.bankodemia.core.showSnackBarMessage
import com.example.bankodemia.core.types.FieldTypeEnum
import com.example.bankodemia.core.utils.*
import com.example.bankodemia.core.validateField
import com.example.bankodemia.databinding.FragmentAddContactBinding
import com.example.bankodemia.domain.domainObjects.Contact.ContactDTO
import com.example.bankodemia.domain.domainObjects.Contact.ContactPostDTO
import com.example.bankodemia.ui.view.transaction.SendFragment
import com.example.bankodemia.ui.viewModel.AddContactViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_contact.*

class AddContactFragment : Fragment(), Fields {
    private var _binding: FragmentAddContactBinding? = null
    private var contact: ContactDTO? = null
    private lateinit var communicator: FragmentCommunicator
    private lateinit var addContactViewModel: AddContactViewModel
    private val binding get() = _binding!!
    private val bankArray = arrayOf("Bankodemia", "Banorte", "Santander", "CityBanamex")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addContactViewModel =
            ViewModelProvider(this).get(AddContactViewModel::class.java)
        communicator = requireActivity() as FragmentCommunicator
        _binding = FragmentAddContactBinding.inflate(inflater, container, false)
        contact = arguments?.getSerializable(CONTACTDATA) as ContactDTO

        setupObservers()
        validationFields()
        setEvents()
        setupView(contact)
        return binding.root
    }

    private fun setupObservers() {
        addContactViewModel.uiStateEmitter.observe(viewLifecycleOwner) { updateUI(it) }
    }

    private fun updateUI(uiState: BaseUiState) {
        when (uiState) {
            is BaseUiState.SuccessResult<*> -> {
                if (uiState.result is ContactPostDTO) {
                    val contactInfo = uiState.result as ContactPostDTO
                }
            }
            is BaseUiState.Error -> {
                showSnackBarMessage(uiState.message ?: general, Snackbar.LENGTH_LONG)
            }
        }
    }

    private fun setupView(contact: ContactDTO?) {
        val bank = RandomString(bankArray).rollBank()
        if (contact != null) {
            binding.apply {
                addContactTietCardNumber.hint = contact.user.id
                addContactTietInstitution.hint = bank
                addContactTietEmail.hint = contact.user.email
            }
        }
    }

    override fun validationFields() {
        var checkFullName = false
        with(binding) {
            addContactTietName.addTextChangedListener {
                checkFullName = validateField(
                    fragment = this@AddContactFragment,
                    typeEnum = FieldTypeEnum.TEXT,
                    addContactTilName
                )
                activateButton(
                    fragment = this@AddContactFragment,
                    addContactBtnAddContact,
                    checkFullName
                )
            }
        }
    }

    private fun setEvents() {
        binding.addContactBtnBackToSend.setOnClickListener { view: View ->
            communicator.goTo(SendFragment())
        }

        binding.addContactBtnAddContact.setOnClickListener { view: View ->
            val cardId = contact?._id
            val shortName = addContactTietName.text?.trim().toString()
            if (cardId.isNullOrEmpty()) {
            } else {
                addContactViewModel.updateContact(cardId, shortName)
                communicator.goTo(ContactAddedFragment())
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}