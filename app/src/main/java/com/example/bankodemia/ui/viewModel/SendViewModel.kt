package com.example.bankodemia.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.core.utils.LocalErrorCodes
import com.example.bankodemia.data.model.EntityException
import com.example.bankodemia.domain.useCase.GetUsersByQueryUseCase
import kotlinx.coroutines.launch

class SendViewModel : BaseViewModel() {
    private val getUsersUseCase = GetUsersByQueryUseCase()

    fun getUsers() {
        viewModelScope.launch {
            uiStateEmitter.value = BaseUiState.loading
            val result = getUsersUseCase.invoke()
            if (result == null) {
                uiStateEmitter.value = BaseUiState.Error(
                    EntityException.Local(
                        LocalErrorCodes.USER_PROFILE_ERROR,
                        "AN ERROR OCURRED IN GET USERS ENDPOINT"
                    )
                )
                return@launch
            }
            val users = result?.let { it } ?: return@launch
            uiStateEmitter.value = BaseUiState.SuccessResult(users)
        }
    }
}