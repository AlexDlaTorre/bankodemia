package com.example.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.bankodemia.core.types.FieldTypeEnum
import com.example.bankodemia.core.activateButton
import com.example.bankodemia.core.validateField
import com.example.bankodemia.databinding.FragmentTelephoneBinding

class TelephoneFragment : Fragment(), Fields {
    private var _binding: FragmentTelephoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelephoneBinding.inflate(inflater, container, false)
        validationFields()
        return binding.root
    }

    override fun validationFields() {
        var telephone: Boolean
        with(binding) {
            telephoneTietTelephone.addTextChangedListener {
                telephone = validateField(
                    fragment = this@TelephoneFragment,
                    typeEnum = FieldTypeEnum.PHONE,
                    telephoneTilTelephone
                )
                activateButton(
                    fragment = this@TelephoneFragment,
                    telephoneBtnContinueProcess,
                    telephone
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}