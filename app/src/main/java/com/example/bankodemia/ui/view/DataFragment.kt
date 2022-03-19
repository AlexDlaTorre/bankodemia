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
import com.example.bankodemia.core.validateField
import com.example.bankodemia.databinding.FragmentDataBinding


class DataFragment : Fragment(), Fields {
    private var _binding: FragmentDataBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataBinding.inflate(inflater, container, false)
        initializeComponents()
        validationFields()
        return mBinding.root
    }

    private fun initializeComponents() {
        with(mBinding){
            dataBtnBackToCreateAccount.setOnClickListener {
                findNavController().navigate(R.id.action_dataFragment_to_createAccountFragment)
            }

            dataBtnContinue.setOnClickListener {
                findNavController().navigate(R.id.action_dataFragment_to_telephoneFragment)
            }
        }
    }

    override fun validationFields() {
        var checkName = false
        var checkLastName = false
        var checkOccupation = false
        var checkBirthday = false

        with(mBinding) {
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
