package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.core.types.FieldTypeEnum
import com.example.bankodemia.core.activateButton
import com.example.bankodemia.core.compareEqualsFields
import com.example.bankodemia.core.validateField
import com.example.bankodemia.databinding.FragmentPasswordBinding

class PasswordFragment : Fragment(), Fields {
    private var _binding: FragmentPasswordBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        initializeComponents()
        validationFields()
        return mBinding.root
    }

    private fun initializeComponents() {
        with(mBinding){
            passwordBtnCreatePassword.setOnClickListener {
                // TODO - Elaborar metodo para la llamada a la api y el loading
                findNavController().navigate(R.id.action_passwordFragment_to_confirmationFragment)
            }

            passwordBtnBackToIdentityMenu.setOnClickListener {
                findNavController().navigate(R.id.action_passwordFragment_to_identityMenuFragment)
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