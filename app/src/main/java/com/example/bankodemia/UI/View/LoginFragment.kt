package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.bankodemia.core.FieldTypeEnum
import com.example.bankodemia.core.activateButton
import com.example.bankodemia.core.validateField
import com.example.bankodemia.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment(), Fields {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        validationFields()
        return binding.root
    }

    override fun validationFields() {
        var checkMail = false
        var checkPassword = false

        with(binding){
            loginTietPassword.addTextChangedListener {
                checkMail = validateField(fragment = this@LoginFragment,typeEnum = FieldTypeEnum.NO_TYPE ,loginTilPassword)
                activateButton(fragment = this@LoginFragment, button = loginBtnLogin, checkMail, checkPassword)
            }

            loginTietMail.addTextChangedListener {
                checkPassword = validateField(fragment = this@LoginFragment,typeEnum = FieldTypeEnum.EMAIL,loginTilMail)
                activateButton(fragment = this@LoginFragment, button = loginBtnLogin , checkMail, checkPassword)
            }

            loginBtnLogin.setOnClickListener {
                Snackbar.make(binding.root,"Di click",Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}