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
import com.example.bankodemia.databinding.FragmentCreateAccountBinding


class CreateAccountFragment : Fragment(), Fields {
    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        validationFields()
        return binding.root
    }

    override fun validationFields() {
        var checkMail: Boolean
        with(binding) {
            createAccountTietMail.addTextChangedListener {
                checkMail = validateField(
                    fragment = this@CreateAccountFragment,
                    typeEnum = FieldTypeEnum.EMAIL,
                    createAccountTilMail
                )
                activateButton(
                    fragment = this@CreateAccountFragment,
                    createAccountBtnContinue,
                    checkMail
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}