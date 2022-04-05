package com.example.bankodemia.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bankodemia.R
import com.example.bankodemia.core.activateButton
import com.example.bankodemia.core.types.FieldTypeEnum
import com.example.bankodemia.core.utils.CONTACTDATA
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.core.utils.RandomString
import com.example.bankodemia.core.utils.USERDATA
import com.example.bankodemia.core.validateField
import com.example.bankodemia.databinding.FragmentAddContactBinding
import com.example.bankodemia.domain.domainObjects.Contact.ContactDTO
import com.example.bankodemia.domain.domainObjects.User.UserDTO
import com.example.bankodemia.ui.view.transaction.SendFragment
import com.example.bankodemia.ui.viewModel.AddContactViewModel
import kotlinx.android.synthetic.main.fragment_add_contact.*

class AddContactFragment : Fragment(), Fields {
    private var _binding: FragmentAddContactBinding? = null
    private var contact: ContactDTO? = null
    private var user: UserDTO? = null
    private lateinit var communicator: FragmentCommunicator
    private lateinit var addContactViewModel: AddContactViewModel
    private val binding get() = _binding!!
    private val bankArray = arrayOf("Bankodemia", "Banorte", "Santander", "CityBanamex")
    var editMode: Boolean = false

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
            editMode = true
            binding.apply {
                addContactTietCardNumber.hint = contact.owner.id
                addContactTietInstitution.hint = bank
                addContactTietEmail.hint = contact.owner.email
                addContactBtnBackToSend.text = "Editar Contacto"
                addContactBtnAddContact.text = "Editar Contacto"
            }
        } else if (user != null) {
            val user = user?.let { it } ?: return
            binding.apply {
                addContactTietCardNumber.hint = user.id
                addContactTietInstitution.hint = bank
                addContactTietEmail.hint = user.email
                addContactBtnBackToSend.text = "Añadir Contacto"
                addContactBtnAddContact.text = "Añadir Contacto"
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
            val idUser = user?.id

                if (cardId != null) {
                    addContactViewModel.updateContact(cardId, shortName)
                    communicator.goTo(ContactEditedFragment())
            } else {
                    if (idUser != null) {
//                        addContactViewModel.createContact(shortName,idUser)
                        communicator.goTo(ContactAddedFragment())
                    }
                    communicator.goTo(AddContactFragment())
        }
    }

    fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}}