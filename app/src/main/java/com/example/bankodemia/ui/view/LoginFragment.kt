package com.example.bankodemia.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.UI.viewModel.LoginViewModel
import com.example.bankodemia.core.activateButton
import com.example.bankodemia.core.instances.SharedPreferencesInstance
import com.example.bankodemia.core.showSnackBarMessage
import com.example.bankodemia.core.types.FieldTypeEnum
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.core.utils.FragmentCommunicator
import com.example.bankodemia.core.utils.general
import com.example.bankodemia.core.validateField
import com.example.bankodemia.data.model.Auth
import com.example.bankodemia.databinding.FragmentLoginBinding
import com.example.bankodemia.domain.domainObjects.Auth.AuthDTO

class LoginFragment : Fragment(), Fields {

    private var _binding: FragmentLoginBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var mViewModel: LoginViewModel
    private lateinit var mCommunicator: FragmentCommunicator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        mCommunicator = requireActivity() as FragmentCommunicator

        setupObservers()
        initializeComponents()
        validationFields()
        return mBinding.root
    }

    private fun initializeComponents() {
        with(mBinding) {
            loginBtnBackToMain.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }

            loginBtnLogin.setOnClickListener {
                with(mBinding) {
                    val email = loginTietMail.text?.trim().toString()
                    val password = loginTietPassword.text?.trim().toString()
                    mViewModel.logIn(Auth.AuthLogIn(email = email, password = password))
                }

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
                if (uiState.result is AuthDTO) {
                    val authInfo = uiState.result as AuthDTO
                    if (!authInfo.token.isNullOrBlank() && !authInfo.token.isNullOrEmpty()) {
                        Log.d("LoginFragment", authInfo.token)
                        SharedPreferencesInstance.saveToken(token = authInfo.token)
                        val intent = Intent(activity, HomeActivity::class.java)
                        startActivity(intent)
                        activity?.finish()
                    }
                }
            }
            is BaseUiState.Error -> {
                mCommunicator.showLoader(false)
                showSnackBarMessage(uiState.message ?: general, Toast.LENGTH_SHORT)
            }
            is BaseUiState.loading -> {
                mCommunicator.showLoader(true)
            }
        }
    }

    override fun validationFields() {
        var checkMail = false
        var checkPassword = false

        with(mBinding) {
            loginTietPassword.addTextChangedListener {
                checkMail = validateField(
                    fragment = this@LoginFragment,
                    typeEnum = FieldTypeEnum.NO_TYPE,
                    loginTilPassword
                )
                activateButton(
                    fragment = this@LoginFragment,
                    button = loginBtnLogin,
                    checkMail,
                    checkPassword
                )
            }

            loginTietMail.addTextChangedListener {
                checkPassword = validateField(
                    fragment = this@LoginFragment,
                    typeEnum = FieldTypeEnum.EMAIL,
                    loginTilMail
                )
                activateButton(
                    fragment = this@LoginFragment,
                    button = loginBtnLogin,
                    checkMail,
                    checkPassword
                )
            }
        }
    }
}