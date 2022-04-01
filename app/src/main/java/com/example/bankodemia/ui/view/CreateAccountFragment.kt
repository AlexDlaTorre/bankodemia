package com.example.bankodemia.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.core.Extensions.TAG
import com.example.bankodemia.core.types.FieldTypeEnum
import com.example.bankodemia.core.activateButton
import com.example.bankodemia.core.instances.SharedPreferencesInstance
import com.example.bankodemia.core.showSnackBarMessage
import com.example.bankodemia.core.showToastMessage
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.core.utils.general
import com.example.bankodemia.core.validateField
import com.example.bankodemia.databinding.FragmentCreateAccountBinding
import com.example.bankodemia.domain.domainObjects.Auth.AuthDTO
import com.example.bankodemia.domain.domainObjects.User.UserDTO
import com.example.bankodemia.domain.domainObjects.User.getUsers.UserGetResponseDTO
import com.example.bankodemia.ui.viewModel.SearchUserViewModel
import com.google.android.material.snackbar.Snackbar


class CreateAccountFragment : Fragment(), Fields {
    private var _binding: FragmentCreateAccountBinding? = null
    private val mBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        initializeComponents()
        validationFields()
        return mBinding.root
    }

    private fun initializeComponents() {
        var email = SharedPreferencesInstance.getStringValue(getString(R.string.saved_mail))
        with(mBinding) {
            createAccountTietMail.setText(email)

            if (!email.isNullOrEmpty()) {
                validateInfo()
            }

            createAccountBtnContinue.setOnClickListener {
                email = createAccountTietMail.text?.trim().toString()
                SharedPreferencesInstance.setStringValue(getString(R.string.saved_mail), email!!)
                findNavController().navigate(R.id.action_createAccountFragment_to_dataFragment)
            }

            createAccountBtnBackToMain.setOnClickListener {
                findNavController().navigate(R.id.action_createAccountFragment_to_mainFragment)
            }
        }
    }

    private fun validateInfo() {
        var checkMail: Boolean
        with(mBinding) {
            checkMail = validateField(
                fragment = this@CreateAccountFragment,
                typeEnum = FieldTypeEnum.EMAIL,
                createAccountTilMail
            )
            activateButton(
                fragment = this@CreateAccountFragment,
                createAccountBtnContinue,
                checkMail
            )
        }
    }

    override fun validationFields() {
        var checkMail: Boolean
        with(mBinding) {
            createAccountTietMail.addTextChangedListener {
                checkMail = validateField(
                    fragment = this@CreateAccountFragment,
                    typeEnum = FieldTypeEnum.EMAIL,
                    createAccountTilMail
                )
                activateButton(
                    fragment = this@CreateAccountFragment,
                    createAccountBtnContinue,
                    checkMail
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}