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
import com.example.bankodemia.databinding.FragmentCreateAccountBinding


class CreateAccountFragment : Fragment(), Fields {
    private var _binding: FragmentCreateAccountBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        initializeComponents()
        validationFields()
        return mBinding.root
    }

    private fun initializeComponents() {
        with(mBinding){
            createAccountBtnContinue.setOnClickListener {
                findNavController().navigate(R.id.action_createAccountFragment_to_dataFragment)
            }

            createAccountBtnBackToMain.setOnClickListener {
                findNavController().navigate(R.id.action_createAccountFragment_to_mainFragment)
            }
        }
    }

    override fun validationFields() {
        var checkMail: Boolean
        with(mBinding) {
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