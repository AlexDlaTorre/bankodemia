package com.example.bankodemia.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.UI.view.DatePickerFragment
import com.example.bankodemia.core.Extensions.TAG
import com.example.bankodemia.core.types.FieldTypeEnum
import com.example.bankodemia.core.activateButton
import com.example.bankodemia.core.instances.SharedPreferencesInstance
import com.example.bankodemia.core.validateField
import com.example.bankodemia.databinding.FragmentDataBinding
import java.text.SimpleDateFormat


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
        var name = SharedPreferencesInstance.getStringValue(getString(R.string.saved_name))
        var lastName = SharedPreferencesInstance.getStringValue(getString(R.string.saved_last_name))
        var occupation = SharedPreferencesInstance.getStringValue(getString(R.string.saved_ocuppation))
        val birthday = SharedPreferencesInstance.getStringValue(getString(R.string.saved_birthday))


        with(mBinding) {
            dataTietName.setText(name)
            dataTietLastName.setText(lastName)
            dataTietOccupation.setText(occupation)
            dataTietBirthday.setText(birthday)

            if (!name.isNullOrEmpty() && !lastName.isNullOrEmpty() && !occupation.isNullOrEmpty() && !birthday.isNullOrEmpty()) {
                validateInfo()
            }

            dataBtnBackToCreateAccount.setOnClickListener {
                findNavController().navigate(R.id.action_dataFragment_to_createAccountFragment)
            }

            dataBtnContinue.setOnClickListener {
                name = dataTietName.text?.trim().toString()
                SharedPreferencesInstance.setStringValue(getString(R.string.saved_name), name!!)
                lastName = dataTietLastName.text?.trim().toString()
                SharedPreferencesInstance.setStringValue(getString(R.string.saved_last_name), lastName!!)
                occupation = dataTietOccupation.text?.trim().toString()
                SharedPreferencesInstance.setStringValue(getString(R.string.saved_ocuppation), occupation!!)
                findNavController().navigate(R.id.action_dataFragment_to_telephoneFragment)
            }

            dataTietBirthday.setOnClickListener {
                showDatePickerDialog()
            }

        }
    }

    private fun validateInfo() {
        with(mBinding) {
            val checkName = validateField(
                fragment = this@DataFragment,
                typeEnum = FieldTypeEnum.TEXT,
                dataTilName
            )

            val checkLastName = validateField(
                fragment = this@DataFragment,
                typeEnum = FieldTypeEnum.TEXT,
                dataTilLastName
            )

            val checkOccupation = validateField(
                fragment = this@DataFragment,
                typeEnum = FieldTypeEnum.TEXT,
                dataTilOccupation
            )

            val checkBirthday = validateField(
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

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year ->
            onDateSelected(day, month, year)
        }
        datePicker.show(activity?.supportFragmentManager!!, "datePicker")
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    private fun onDateSelected(day: Int, month: Int, year: Int) {
        val formatMonth = if (month < 10) "0${month + 1}" else "${month + 1}"
        val formatDay = if (day < 10) "0$day" else "$day"
        val birthday = "$year-$formatMonth-$formatDay"
        mBinding.dataTietBirthday.setText(birthday)
        SharedPreferencesInstance.setStringValue(getString(R.string.saved_birthday), birthday)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
