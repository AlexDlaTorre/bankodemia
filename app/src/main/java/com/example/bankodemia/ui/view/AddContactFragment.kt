package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.bankodemia.core.types.FieldTypeEnum
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.ui.viewModel.AddContactViewModel
import com.example.bankodemia.core.activateButton
import com.example.bankodemia.core.utils.CONTACTDATA
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.core.validateField
import com.example.bankodemia.databinding.FragmentAddContactBinding
import com.example.bankodemia.domain.domainObjects.Contact.ContactDTO
import com.example.bankodemia.domain.domainObjects.Contact.ContactDeleteDTO
import com.example.bankodemia.ui.view.transaction.SendFragment
import kotlinx.android.synthetic.main.fragment_transfer_detail.*

class AddContactFragment : Fragment(), Fields {
    private var _binding: FragmentAddContactBinding? = null
    private var contact: ContactDTO? = null
    private lateinit var communicator: FragmentCommunicator
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val addContactViewModel =
            ViewModelProvider(this).get(AddContactViewModel::class.java)

        _binding = FragmentAddContactBinding.inflate(inflater, container, false)
        contact = arguments?.getSerializable(CONTACTDATA) as ContactDTO

        binding.addContactBtnBackToSend.setOnClickListener { view : View ->
            communicator.goTo(SendFragment())
        }

        binding.addContactBtnAddContact.setOnClickListener { view : View ->
            communicator.goTo(ContactAddedFragment())
        }

        setupView()
        validationFields()
        return binding.root
    }

    private fun setupView() {
        val contact = contact ?: return
        binding.apply {
            addContactTilCardNumber.hint = contact.user.id
            addContactTietInstitution.hint = contact.user.fullName
            addContactTietInstitution.hint = contact.user.fullName
            addContactTilName.hint = contact.shortName
            addContactTilEmail.hint = contact.user.email
            addContactTietEmail.hint = contact.user.email
            val shortName = addContactTietName.text?.trim().toString()

        }
    }


    override fun validationFields() {
        var checkNumber = false
        var checkInstitution= false
        var checkFullName = false
        var checkMail = false

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}