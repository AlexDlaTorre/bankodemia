package com.example.bankodemia.ui.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bankodemia.R
import com.example.bankodemia.databinding.FragmentDataBinding

class DataFragment : Fragment() {
    private var _binding: FragmentDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataBinding.inflate(inflater, container,false)
        validationInputs()
        return binding.root
    }

    private fun validationInputs() {
        var checkName = false
        var checkLastName = false
        var checkOccupation = false
        var checkBirthday = false

        binding.dataTietName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(editText: Editable?) {
                val name = binding.dataTietName.text.toString()
                val errorNameStr = if (name.isEmpty()) {
                    checkName = false
                    "Campo Requerido"
                } else {
                    checkName = true
                    null
                }
                binding.dataTilName.error = errorNameStr

                if (checkName && checkLastName && checkOccupation && checkBirthday) {
                    binding.dataBtnContinue.isEnabled = true
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.dataBtnContinue.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.dataBtnContinue.isEnabled = false
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.dataBtnContinue.setBackgroundColor(resources.getColor(R.color.light_gray_inactive))
                }
            }
        })

        binding.dataTietLastName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(editText: Editable?) {
                val lastName = binding.dataTietLastName.text.toString()
                val errorLastNameStr = if (lastName.isEmpty()) {
                    checkLastName = false
                    "Campo Requerido"
                } else {
                    checkLastName = true
                    null
                }
                binding.dataTilLastName.error = errorLastNameStr

                if (checkName && checkLastName && checkOccupation && checkBirthday) {
                    binding.dataBtnContinue.isEnabled = true
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.dataBtnContinue.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.dataBtnContinue.isEnabled = false
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.dataBtnContinue.setBackgroundColor(resources.getColor(R.color.light_gray_inactive))
                }
            }
        })

        binding.dataTietOccupation.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(editText: Editable?) {
                val occupation = binding.dataTietOccupation.text.toString()
                val errorOccupationStr = if (occupation.isEmpty()) {
                    checkOccupation = false
                    "Campo Requerido"
                } else {
                    checkOccupation = true
                    null
                }
                binding.dataTilOccupation.error = errorOccupationStr

                if (checkName && checkLastName && checkOccupation && checkBirthday) {
                    binding.dataBtnContinue.isEnabled = true
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.dataBtnContinue.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.dataBtnContinue.isEnabled = false
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.dataBtnContinue.setBackgroundColor(resources.getColor(R.color.light_gray_inactive))
                }
            }
        })

        binding.dataTietBirthday.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(editText: Editable?) {
                val birthday = binding.dataTietBirthday.text.toString()
                val errorBirthdayStr = if (birthday.isEmpty()) {
                    checkBirthday = false
                    "Campo Requerido"
                } else {
                    checkBirthday = true
                    null
                }
                binding.dataTilBirthday.error = errorBirthdayStr

                if (checkName && checkLastName && checkOccupation && checkBirthday) {
                    binding.dataBtnContinue.isEnabled = true
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.dataBtnContinue.setBackgroundColor(resources.getColor(R.color.blue))
                } else {
                    binding.dataBtnContinue.isEnabled = false
                    //TODO : Buscar metodo que permita obtener el color desde los resources
                    binding.dataBtnContinue.setBackgroundColor(resources.getColor(R.color.light_gray_inactive))
                }
            }
        })
    }
}