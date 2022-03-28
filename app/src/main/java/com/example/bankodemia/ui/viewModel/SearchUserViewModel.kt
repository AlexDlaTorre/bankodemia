package com.example.bankodemia.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.domain.useCase.GetUsersByQueryUseCase
import kotlinx.coroutines.launch

class SearchUserViewModel: BaseViewModel() {
    private val getUsersByQueryUseCase = GetUsersByQueryUseCase()

    fun getUser(query: String) {
        viewModelScope.launch {
            uiStateEmitter.value = BaseUiState.loading
            val result = getUsersByQueryUseCase.invoke(query)
            if (result.second != null) {
                val error = result?.let { it.second?.let { it } } ?: return@launch
                uiStateEmitter.value = BaseUiState.Error(error)
            }
            val user = result?.let { it.first?.let { it } } ?: return@launch
            uiStateEmitter.value = BaseUiState.SuccessResult(user)
        }
    }
}