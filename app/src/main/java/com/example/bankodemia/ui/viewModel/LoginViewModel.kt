package com.example.bankodemia.UI.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.core.utils.LocalErrorCodes
import com.example.bankodemia.data.model.Auth
import com.example.bankodemia.data.model.EntityException
import com.example.bankodemia.domain.useCase.LogInUseCase
import com.example.bankodemia.ui.viewModel.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    private val logInUseCase = LogInUseCase()

    fun logIn(login: Auth.AuthLogIn) {
        viewModelScope.launch {
            uiStateEmitter.value = BaseUiState.loading
            val result = logInUseCase.invoke(login)
            if (result == null) {
                uiStateEmitter.value = BaseUiState.Error(
                    EntityException.Local(
                        LocalErrorCodes.LOGIN_ERROR,
                        "AN ERROR OCURRED IN LOG IN POST ENDPOINT"
                    )
                )
                return@launch
            }
            val authInfo = result?.let { it } ?: return@launch
            uiStateEmitter.value = BaseUiState.SuccessResult(authInfo)
        }
    }
}