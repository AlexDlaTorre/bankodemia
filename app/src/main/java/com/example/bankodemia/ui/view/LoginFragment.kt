package com.example.bankodemia.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.UI.viewModel.LoginViewModel
import com.example.bankodemia.core.types.FieldTypeEnum
import com.example.bankodemia.core.activateButton
import com.example.bankodemia.core.showToastMessage
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.core.validateField
import com.example.bankodemia.databinding.FragmentLoginBinding
import com.example.bankodemia.domain.domainObjects.Auth.AuthDTO
import com.example.bankodemia.domain.domainObjects.User.geUserProfile.UserProfileDTO
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment(), Fields {
    private var _binding: FragmentLoginBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var mViewModel : LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        setupObservers()
        initializeComponents()
        validationFields()
        return mBinding.root
    }

    private fun initializeComponents() {
        with(mBinding){
            loginBtnBackToMain.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }

            loginBtnLogin.setOnClickListener {
                val intent = Intent(activity,HomeActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }
    }

    private fun setupObservers() {
        mViewModel.uiStateEmitter.observe(viewLifecycleOwner) { updateUI(it) }
    }

    private fun updateUI(uiState: BaseUiState?) {
        when (uiState) {
            is BaseUiState.SuccessResult<*> -> {
                if (uiState.result is AuthDTO) {
                    val userProfileInfo = uiState.result as AuthDTO
                }
            }
            is BaseUiState.Error -> {
                showToastMessage(uiState.error.localizedMessage, Toast.LENGTH_SHORT)
            }
            is BaseUiState.loading -> {
                setupReclycerView(mutableListOf(), true)
            }
        }
    }


    override fun validationFields() {
        var checkMail = false
        var checkPassword = false

        with(mBinding){
            loginTietPassword.addTextChangedListener {
                checkMail = validateField(fragment = this@LoginFragment,typeEnum = FieldTypeEnum.NO_TYPE ,loginTilPassword)
                activateButton(fragment = this@LoginFragment, button = loginBtnLogin, checkMail, checkPassword)
            }

            loginTietMail.addTextChangedListener {
                checkPassword = validateField(fragment = this@LoginFragment,typeEnum = FieldTypeEnum.EMAIL,loginTilMail)
                activateButton(fragment = this@LoginFragment, button = loginBtnLogin , checkMail, checkPassword)
            }
        }
    }
}