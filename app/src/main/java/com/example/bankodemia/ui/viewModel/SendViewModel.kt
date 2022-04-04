package com.example.bankodemia.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.domain.useCase.DeleteContactIdUseCase
import com.example.bankodemia.domain.useCase.GetContactsListUseCase
import kotlinx.coroutines.launch

class SendViewModel : BaseViewModel() {
    val getContactsListUseCase = GetContactsListUseCase()
    val deleteContactIdUseCase = DeleteContactIdUseCase()

    fun getContactsListData() {
        viewModelScope.launch {
            uiStateEmitter.value = BaseUiState.loading
            val result = getContactsListUseCase.invoke()
            if (result.second != null) {
                val error = result.let { it.second?.let { it } } ?: return@launch
                uiStateEmitter.value = BaseUiState.Error(error)
            }
            val response = result.let { it.first?.let { it } } ?: return@launch
            uiStateEmitter.value = BaseUiState.SuccessResult(response)
        }
    }

    fun deleteContact(id: String) {
        viewModelScope.launch {
            uiStateEmitter.value = BaseUiState.loading
            val result = deleteContactIdUseCase.invoke(id)
            if (result.second != null) {
                val error = result.let { it.second?.let { it } } ?: return@launch
                uiStateEmitter.value = BaseUiState.Error(error)
            }
            val responseContactDelete =
                result.let { it.first?.let { it } } ?: return@launch
            uiStateEmitter.value = BaseUiState.SuccessResult(responseContactDelete)
        }
    }
}