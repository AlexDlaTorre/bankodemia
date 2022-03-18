package com.example.bankodemia.ui.view.deposit

import androidx.lifecycle.viewModelScope
import com.example.bankodemia.core.types.MovementType
import com.example.bankodemia.core.utils.*
import com.example.bankodemia.data.model.EntityException
import com.example.bankodemia.domain.useCase.MakeTransactionUseCase
import com.example.bankodemia.ui.viewModel.BaseViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class DepositViewModel: BaseViewModel() {
    private val makeTransactionUseCase = MakeTransactionUseCase()

    fun makeDeposit(amount: Int,
                    type: MovementType,
                    concept: String) {
        viewModelScope.launch {
            uiStateEmitter.value = BaseUiState.loading
            val parameters = makeDepositParameteres(amount,
                                                    type,
                                                    concept)
            val result = makeTransactionUseCase.invoke(parameters)
            if (result == null) {
                uiStateEmitter.value = BaseUiState.Error(
                    EntityException.Local(
                        LocalErrorCodes.USER_PROFILE_ERROR,
                        "AN ERROR OCURRED IN MAKE TRANSACTION ENDPOINT"
                    )
                )
                return@launch
            }
            val transactionInfo = result?.let { it } ?: return@launch
            uiStateEmitter.value = BaseUiState.SuccessResult(transactionInfo)
        }
    }

    private fun makeDepositParameteres(amount: Int,
                                       type: MovementType,
                                       concept: String): RequestBody {
        val parameters = JSONObject()
        parameters.put(amountBodyKey, amount)
        parameters.put(typeBodyKey, type.type)
        parameters.put(conceptBodyKey, concept)
        return RequestBody.create(MediaType.parse(jsonFormat), parameters.toString())
    }
}