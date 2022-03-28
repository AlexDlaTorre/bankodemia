package com.example.bankodemia.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.core.utils.LocalErrorCodes
import com.example.bankodemia.data.model.EntityException
import com.example.bankodemia.domain.useCase.GetUsersByQueryUseCase
import kotlinx.coroutines.launch

class SendViewModel : BaseViewModel() {
    private val getUsersUseCase = GetUsersByQueryUseCase()
}