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
import com.example.bankodemia.databinding.FragmentTelephoneBinding

class TelephoneFragment : Fragment() {
    private var _binding: FragmentTelephoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelephoneBinding.inflate(inflater, container, false)
        validationInputs()
        return binding.root
    }

    private fun validationInputs() {
        binding.telephoneTietTelephone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editText: Editable?) {
                val telephone = binding.telephoneTietTelephone.text.toString()

                val errorTelephoneStr = if (telephone.isEmpty()) {
                    "Campo Requerido"
                } else if (!validateTelephone(telephone)) {
                    "Telefono no valido"
                } else {
                    null
                }
                binding.telephoneTilTelephone.error = errorTelephoneStr
                if (errorTelephoneStr == null) {
                    binding.telephoneBtnContinueProcess.isEnabled = true
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.telephoneBtnContinueProcess.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.telephoneBtnContinueProcess.isEnabled = false
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.telephoneBtnContinueProcess.setBackgroundColor(resources.getColor(R.color.light_gray_inactive))
                }
            }
        })
    }

    private fun validateTelephone(telephone: String): Boolean {
        if (!telephone.matches(".*[0-9].*".toRegex())) {
            return false
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}