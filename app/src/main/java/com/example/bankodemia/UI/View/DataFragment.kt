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
import com.example.bankodemia.databinding.FragmentDataBinding


class DataFragment : Fragment(), Fields {
    private var _binding: FragmentDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataBinding.inflate(inflater, container, false)
        validationFields()
        return binding.root
    }

    override fun validationFields() {
        var checkName = false
        var checkLastName = false
        var checkOccupation = false
        var checkBirthday = false

        with(binding) {
            dataTietName.addTextChangedListener {
                checkName = validateField(
                    fragment = this@DataFragment,
                    typeEnum = FieldTypeEnum.TEXT,
                    dataTilName
                )
                activateButton(
                    fragment = this@DataFragment,
                    dataBtnContinue,
                    checkName, checkLastName, checkOccupation, checkBirthday
                )
            }

            dataTietLastName.addTextChangedListener {
                checkLastName = validateField(
                    fragment = this@DataFragment,
                    typeEnum = FieldTypeEnum.TEXT,
                    dataTilLastName
                )
                activateButton(
                    fragment = this@DataFragment,
                    dataBtnContinue,
                    checkName, checkLastName, checkOccupation, checkBirthday
                )
            }

            dataTietOccupation.addTextChangedListener {
                checkOccupation = validateField(
                    fragment = this@DataFragment,
                    typeEnum = FieldTypeEnum.TEXT,
                    dataTilOccupation
                )
                activateButton(
                    fragment = this@DataFragment,
                    dataBtnContinue,
                    checkName, checkLastName, checkOccupation, checkBirthday
                )
            }

            dataTietBirthday.addTextChangedListener {
                checkBirthday = validateField(
                    fragment = this@DataFragment,
                    typeEnum = FieldTypeEnum.DATE,
                    dataTilBirthday
                )
                activateButton(
                    fragment = this@DataFragment,
                    dataBtnContinue,
                    checkName, checkLastName, checkOccupation, checkBirthday
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
