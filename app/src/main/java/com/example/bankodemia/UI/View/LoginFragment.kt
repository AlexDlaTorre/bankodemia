package com.example.bankodemia.ui.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bankodemia.R
import com.example.bankodemia.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        validationInputs()
        return binding.root
    }

    private fun validationInputs() {
        var checkMail = false
        var checkPassword = false

        binding.loginTietMail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editText: Editable?) {
                val correo = binding.loginTietMail.text.toString()
                val errorMailStr = if (correo.isEmpty()) {
                    checkMail = false
                    "Campo Requerido"
                } else if (!validateMail(correo)) {
                    checkMail = false
                    "Correo no valido"
                } else {
                    checkMail = true
                    null
                }
                binding.loginTilMail.error = errorMailStr

                if (checkMail && checkPassword) {
                    binding.loginBtnLogin.isEnabled = true
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.loginBtnLogin.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.loginBtnLogin.isEnabled = false
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.loginBtnLogin.setBackgroundColor(resources.getColor(R.color.light_gray_inactive))
                }
            }
        })

        binding.loginTietPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editText: Editable?) {
                val password = binding.loginTietPassword.text.toString()
                val errorPasswordStr = if (password.isEmpty()) {
                    checkPassword = false
                    "Campo Requerido"
                } else {
                    checkPassword = true
                    null
                }
                binding.loginTilPassword.error = errorPasswordStr

                if (checkMail && checkPassword) {
                    binding.loginBtnLogin.isEnabled = true
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.loginBtnLogin.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.loginBtnLogin.isEnabled = false
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.loginBtnLogin.setBackgroundColor(resources.getColor(R.color.light_gray_inactive))
                }
            }
        })
    }

    private fun validateMail(mail: String): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            return false
        }
        return true
    }
}