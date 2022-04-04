package com.example.bankodemia.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.data.model.User
import com.example.bankodemia.domain.useCase.CreateUserAccountUseCase
import kotlinx.coroutines.launch

class PasswordViewModel : BaseViewModel() {
    val mCreateUserAccount = CreateUserAccountUseCase()

    fun createUserAccount(user: User.SingUpCreateUser){
        viewModelScope.launch {
            uiStateEmitter.value = BaseUiState.loading
            val result = mCreateUserAccount.invoke(user)
            if (result.second != null) {
                val error = result?.let { it.second?.let { it } } ?: return@launch
                uiStateEmitter.value = BaseUiState.Error(error)
            }
            val response = result?.let { it.first?.let { it } } ?: return@launch
            uiStateEmitter.value = BaseUiState.SuccessResult(response)
        }
    }
}