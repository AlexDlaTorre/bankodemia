package com.example.bankodemia.UI.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.UI.viewModel.AddContactViewModel
import com.example.bankodemia.UI.viewModel.CardsViewModel
import com.example.bankodemia.core.FieldTypeEnum
import com.example.bankodemia.core.activateButton
import com.example.bankodemia.core.validateField
import com.example.bankodemia.databinding.FragmentAddContactBinding
import com.example.bankodemia.ui.view.Fields

class AddContactFragment : Fragment(), Fields {
    private var _binding: FragmentAddContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val addContactViewModel =
            ViewModelProvider(this).get(AddContactViewModel::class.java)

        _binding = FragmentAddContactBinding.inflate(inflater, container, false)

        binding.addContactBtnBackToSend.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_addContactFragment_to_sendFragment)
        }

        validationFields()
        return binding.root
    }

    override fun validationFields() {
        var checkNumber = false
        var checkInstitution= false
        var checkFullName = false
        var checkMail = false

        with(binding) {
            addContactTietCardNumber.addTextChangedListener {
                checkNumber = validateField(
                    fragment = this@AddContactFragment,
                    typeEnum = FieldTypeEnum.NUMBER,
                    addContactTilCardNumber
                )
                activateButton(
                    fragment = this@AddContactFragment,
                    addContactBtnAddContact,
                    checkNumber, checkInstitution, checkFullName, checkMail
                )
            }

            addContactTietInstitution.addTextChangedListener {
                checkInstitution = validateField(
                    fragment = this@AddContactFragment,
                    typeEnum = FieldTypeEnum.TEXT,
                    addContactTilInstitution
                )
                activateButton(
                    fragment = this@AddContactFragment,
                    addContactBtnAddContact,
                    checkNumber, checkInstitution, checkFullName, checkMail
                )
            }

            addContactTietName.addTextChangedListener {
                checkFullName = validateField(
                    fragment = this@AddContactFragment,
                    typeEnum = FieldTypeEnum.TEXT,
                    addContactTilName
                )
                activateButton(
                    fragment = this@AddContactFragment,
                    addContactBtnAddContact,
                    checkNumber, checkInstitution, checkFullName, checkMail
                )
            }

            addContactTietEmail.addTextChangedListener {
                checkMail = validateField(
                    fragment = this@AddContactFragment,
                    typeEnum = FieldTypeEnum.EMAIL,
                    addContactTilEmail
                )
                activateButton(
                    fragment = this@AddContactFragment,
                    addContactBtnAddContact,
                    checkNumber, checkInstitution, checkFullName, checkMail
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}