package com.example.bankodemia.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.core.activateButton
import com.example.bankodemia.core.hideKeyboard
import com.example.bankodemia.core.showSnackBarMessage
import com.example.bankodemia.core.types.FieldTypeEnum
import com.example.bankodemia.core.utils.*
import com.example.bankodemia.core.validateField
import com.example.bankodemia.databinding.FragmentAddContactBinding
import com.example.bankodemia.domain.domainObjects.Contact.ContactDTO
import com.example.bankodemia.domain.domainObjects.Contact.ContactPostDTO
import com.example.bankodemia.domain.domainObjects.User.UserDTO
import com.example.bankodemia.ui.view.transaction.SendFragment
import com.example.bankodemia.ui.viewModel.AddContactViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_contact.*

class AddContactFragment : Fragment(), Fields {
    private var _binding: FragmentAddContactBinding? = null
    private var contact: ContactDTO? = null
    private var user: UserDTO? = null
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
        contact = arguments?.getSerializable(CONTACTDATA) as? ContactDTO
        user = arguments?.getSerializable(USERDATA) as? UserDTO

        validationFields()
        setEvents()
        setupView(contact)
        return binding.root
    }

    private fun setupView(contact: ContactDTO?) {
        val bank = RandomString(bankArray).rollBank()
        if (contact != null) {
            binding.apply {
                addContactTietCardNumber.hint = contact.user.id
                addContactTietInstitution.hint = bank
                addContactTietEmail.hint = contact.user.email
            }
        } else if(user != null) {
            val user = user?.let { it } ?: return
            binding.apply {
                addContactTietCardNumber.hint = user.id
                addContactTietInstitution.hint = bank
                addContactTietEmail.hint = user.email
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
            if (user != null ) {
                view.findNavController().navigate(R.id.action_addContactFragment_to_searchUserFragment)
            } else if (contact != null) {
                view.findNavController().navigate(R.id.action_addContactFragment_to_sendFragment)
            }
        }

        binding.addContactBtnAddContact.setOnClickListener { view: View ->
            val cardId = contact?._id
            val shortName = addContactTietName.text?.trim().toString()
            if (cardId.isNullOrEmpty()) {
            } else {
                addContactViewModel.updateContact(cardId, shortName)
                view.findNavController().navigate(R.id.action_addContactFragment_to_contactAddedFragment)
            }
        }
        binding.containerView.setOnClickListener {
            hideKeyboard()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}