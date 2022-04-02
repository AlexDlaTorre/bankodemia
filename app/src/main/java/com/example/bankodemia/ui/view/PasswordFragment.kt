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
import com.example.bankodemia.core.*
import com.example.bankodemia.core.Extensions.TAG
import com.example.bankodemia.core.types.FieldTypeEnum
import com.example.bankodemia.core.instances.SharedPreferencesInstance
import com.example.bankodemia.core.types.IdentityType
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.core.utils.general
import com.example.bankodemia.data.model.User
import com.example.bankodemia.databinding.FragmentPasswordBinding
import com.example.bankodemia.domain.domainObjects.Auth.AuthDTO
import com.example.bankodemia.domain.domainObjects.User.createUser.UserPostResponseDTO
import com.example.bankodemia.ui.viewModel.PasswordViewModel
import com.google.android.material.snackbar.Snackbar

class PasswordFragment : Fragment(), Fields {
    private var _binding: FragmentPasswordBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var mViewModel: PasswordViewModel
    private lateinit var mCommunicator: FragmentCommunicator
    private lateinit var mUserAccount: User.SingUpCreateUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(this)[PasswordViewModel::class.java]
        mCommunicator = requireActivity() as FragmentCommunicator

        initializeComponents()
        validationFields()
        setupObservers()

        return mBinding.root
    }

    private fun initializeComponents() {

        with(mBinding) {
            passwordBtnCreatePassword.setOnClickListener {
                val documentType = when(SharedPreferencesInstance.getStringValue(getString(R.string.saved_document))){
                    IdentityType.INE.value -> IdentityType.INE
                    IdentityType.PASSPORT.value -> IdentityType.PASSPORT
                    IdentityType.MIGRATION_FORM.value -> IdentityType.MIGRATION_FORM
                    else -> IdentityType.INE
                }

                with(SharedPreferencesInstance) {
                    mUserAccount = User.SingUpCreateUser(
                        email = getStringValue(getString(R.string.saved_mail)).toString(),
                        name = getStringValue(getString(R.string.saved_name)).toString(),
                        lastName =getStringValue(getString(R.string.saved_last_name)).toString(),
                        occupation =getStringValue(getString(R.string.saved_ocuppation)).toString(),
                        birthDate =getStringValue(getString(R.string.saved_birthday)).toString(),
                        password = mBinding.passwordTietPassword.text?.trim().toString(),
                        phone =getStringValue(getString(R.string.saved_telephone_format)).toString(),
                        identityImage =getStringValue(getString(R.string.saved_image_string)).toString(),
                        identityImageType = documentType
                    )
                }

                Log.d(TAG,"${mUserAccount.email} ${mUserAccount.name} ${mUserAccount.lastName} ${mUserAccount.occupation} ${mUserAccount.birthDate} ${mUserAccount.password} ${mUserAccount.phone} ${mUserAccount.identityImage} ${mUserAccount.identityImageType}")

                mViewModel.createUserAccount(mUserAccount)
            }

            passwordBtnBackToIdentityMenu.setOnClickListener {
                findNavController().navigate(R.id.action_passwordFragment_to_identityMenuFragment)
            }
        }
    }

    private fun setupObservers() {
        mViewModel.uiStateEmitter.observe(viewLifecycleOwner) { updateUI(it) }
    }

    private fun updateUI(uiState: BaseUiState?) {
        when (uiState) {
            is BaseUiState.SuccessResult<*> -> {
                Log.d("LoginFragment", uiState.result.toString())
                if (uiState.result is UserPostResponseDTO) {
                    val userInfo = uiState.result as UserPostResponseDTO
                    if (userInfo.success) {
                        findNavController().navigate(R.id.action_passwordFragment_to_confirmationFragment)
                        mCommunicator.showLoader(false)
                    }
                }
            }
            is BaseUiState.Error -> {
                mCommunicator.showLoader(false)
                showSnackBarMessage(uiState.message ?: general, Snackbar.LENGTH_LONG)
            }
            is BaseUiState.loading -> {
                mCommunicator.showLoader(true)
            }
        }
    }

    override fun validationFields() {
        var password = false
        var confirmPassword: Boolean

        with(mBinding) {
            passwordTietPassword.addTextChangedListener {
                password = validateField(
                    fragment = this@PasswordFragment,
                    typeEnum = FieldTypeEnum.PASSWORD,
                    passwordTilPassword
                )

                confirmPassword = compareEqualsFields(
                    fragment = this@PasswordFragment,
                    fieldToCompare = passwordTilConfirmPassword,
                    fieldComparator = passwordTilPassword
                )

                activateButton(
                    fragment = this@PasswordFragment,
                    passwordBtnCreatePassword,
                    password, confirmPassword
                )
            }

            passwordTietConfirmPassword.addTextChangedListener {
                confirmPassword = validateField(
                    fragment = this@PasswordFragment,
                    typeEnum = FieldTypeEnum.NO_TYPE,
                    passwordTilConfirmPassword
                )

                confirmPassword = compareEqualsFields(
                    fragment = this@PasswordFragment,
                    fieldToCompare = passwordTilConfirmPassword,
                    fieldComparator = passwordTilPassword
                )

                activateButton(
                    fragment = this@PasswordFragment,
                    passwordBtnCreatePassword,
                    confirmPassword, password
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}