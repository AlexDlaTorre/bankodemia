package com.example.bankodemia.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.domain.useCase.GetUserProfileInfoUseCase
import kotlinx.coroutines.launch

class CardsViewModel : BaseViewModel() {
    val getUserProfileInfoUseCase = GetUserProfileInfoUseCase()

    fun getUserProfileData() {
        viewModelScope.launch {
            uiStateEmitter.value = BaseUiState.loading
            val result = getUserProfileInfoUseCase.invoke()
            if (result.second != null) {
                val error = result?.let { it.second?.let { it } } ?: return@launch
                uiStateEmitter.value = BaseUiState.Error(error)
            }
            val response = result?.let { it.first?.let { it } } ?: return@launch ?: return@launch
            uiStateEmitter.value = BaseUiState.SuccessResult(response)
        }
    }
}