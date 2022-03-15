package com.example.bankodemia.UI.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.bankodemia.core.types.FieldTypeEnum
import androidx.navigation.findNavController
import com.example.bankodemia.R
import com.example.bankodemia.core.activateButton
import com.example.bankodemia.core.validateField
import com.example.bankodemia.databinding.FragmentTransferDetailBinding
import com.example.bankodemia.ui.view.Fields

class TransferDetailFragment : Frasgment(), Fields {
    private var _binding: FragmentTransferDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransferDetailBinding.inflate(inflater, container, false)

        binding.transferBtnBackToSend.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_transferDetailFragment_to_sendFragment)
        }

        binding.transferBtnMakeTransfer.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_transferDetailFragment_to_processingTransactionFragment)
        }

        validationFields()
        return binding.root
    }

    override fun validationFields() {
        var checkTransfer: Boolean
        with(binding) {
            transferTietAmount.addTextChangedListener {
                checkTransfer = validateField(
                    fragment = this@TransferDetailFragment,
                    typeEnum = FieldTypeEnum.NUMBER,
                    transferTilAmount
                )
                activateButton(
                    fragment = this@TransferDetailFragment,
                    transferBtnMakeTransfer,
                    checkTransfer
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}