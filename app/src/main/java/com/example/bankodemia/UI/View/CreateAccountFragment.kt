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
import com.example.bankodemia.databinding.FragmentCreateAccountBinding


class CreateAccountFragment : Fragment() {
    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        validationInputs()
        return binding.root
    }

    private fun validationInputs() {
        binding.createAccountTietMail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editText: Editable?) {
                val mail = binding.createAccountTietMail.text.toString()

                val errorStr = if (mail.isEmpty()) {
                    "Campo Requerido"
                } else if (!validateMail(mail)) {
                    "mail no valido"
                } else {
                    null
                }
                binding.createAccountTilMail.error = errorStr
                if (errorStr == null) {
                    binding.createAccountBtnContinue.isEnabled = true
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.createAccountBtnContinue.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.createAccountBtnContinue.isEnabled = false
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.createAccountBtnContinue.setBackgroundColor(resources.getColor(R.color.light_gray_inactive))
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}