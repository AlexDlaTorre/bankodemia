package com.example.bankodemia.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.domain.useCase.GetContactsListUseCase
import kotlinx.coroutines.launch

class SendViewModel : BaseViewModel() {
    val getContactsListUseCase = GetContactsListUseCase()

    fun getContactsListData() {
        viewModelScope.launch {
            uiStateEmitter.value = BaseUiState.loading
            val result = getContactsListUseCase.invoke()
            if (result.second != null) {
                val error = result?.let { it.second?.let { it } } ?: return@launch
                uiStateEmitter.value = BaseUiState.Error(error)
            }
            val response = result?.let { it.first?.let { it } } ?: return@launch ?: return@launch
            uiStateEmitter.value = BaseUiState.SuccessResult(response)
        }
    }
}