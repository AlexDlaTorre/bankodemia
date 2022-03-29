package com.example.bankodemia.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankodemia.core.empty
import com.example.bankodemia.core.types.MovementType
import com.example.bankodemia.core.utils.*
import com.example.bankodemia.domain.useCase.MakePaymentUseCase
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class TransferDetailViewModel : BaseViewModel() {
    val makePaymentUseCase = MakePaymentUseCase()

    fun makePayment(amount: Int,
                    type: MovementType,
                    destinationUser: String,
                    concept: String) {
        viewModelScope.launch {
            uiStateEmitter.value = BaseUiState.loading
            val parameters = makePaymentParameters(amount,
                                                   type,
                                                    destinationUser,
                                                    concept)
            val result = makePaymentUseCase.invoke(parameters)
            if (result.second != null) {
                val error = result?.let { it.second?.let { it } } ?: return@launch
                uiStateEmitter.value = BaseUiState.Error(error)
            }
            val transactionInfo = result?.let { it.first?.let { it } } ?: return@launch
            uiStateEmitter.value = BaseUiState.SuccessResult(transactionInfo)
        }
    }

    private fun makePaymentParameters(amount: Int,
                                      type: MovementType,
                                      destinationUser: String,
                                      concept: String): RequestBody {
        val parameters = JSONObject()
        parameters.put(amountBodyKey, amount)
        parameters.put(typeBodyKey, type.type)
        parameters.put(destinationUserKey, destinationUser)
        parameters.put(conceptBodyKey, concept)
        return RequestBody.create(MediaType.parse(jsonFormat), parameters.toString())
    }
}