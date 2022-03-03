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
import com.example.bankodemia.databinding.FragmentPasswordBinding

class PasswordFragment : Fragment() {
    private var _binding: FragmentPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        validationInputs()
        return binding.root
    }

    private fun validationInputs() {
        binding.passwordTietPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editText: Editable?) {
                val password = binding.passwordTietPassword.text.toString()

                val errorPasswordStr = if (password.isEmpty()) {
                    "Campo Requerido"
                } else if (!validatePassword(password)) {
                    "password no valido"
                } else if(password.length < 6){
                    "Se requieren 6 caracteres minimo"
                }else {
                    null
                }
                binding.passwordTilPassword.error = errorPasswordStr
                if (errorPasswordStr == null) {
                    binding.passwordBtnCreatePassword.isEnabled = true
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.passwordBtnCreatePassword.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.passwordBtnCreatePassword.isEnabled = false
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.passwordBtnCreatePassword.setBackgroundColor(resources.getColor(R.color.light_gray_inactive))
                }
            }
        })
    }

    private fun validatePassword(password: String): Boolean {
        if(!password.matches(".*^(?=.*\\d)(?=.*[A-Z])(?!.*(.)\\1)\\S{6,}.*".toRegex())){
            return false
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}