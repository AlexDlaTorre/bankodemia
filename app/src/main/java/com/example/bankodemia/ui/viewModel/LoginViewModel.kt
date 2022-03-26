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
            Log.d("LoginViewModel", result.toString())
            if (result.second != null) {
                val error = result?.let { it.second?.let { it } } ?: return@launch
                Log.d("LoginViewModel", error.toString())
                uiStateEmitter.value = BaseUiState.Error(error)
            }
            val response = result?.let { it.first?.let { it } } ?: return@launch ?: return@launch
            Log.d("LoginViewModel", response.toString())
            uiStateEmitter.value = BaseUiState.SuccessResult(response)
        }
    }
}