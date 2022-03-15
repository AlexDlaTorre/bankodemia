package com.example.bankodemia.UI.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.core.utils.LocalErrorCodes
import com.example.bankodemia.data.model.EntityException
import com.example.bankodemia.domain.domainObjects.User.geUserProfile.UserProfileDTO
import com.example.bankodemia.domain.useCase.GetUserProfileInfoUseCase
import kotlinx.coroutines.launch

class HomeViewModel: BaseViewModel() {
    val getUserProfileInfoUseCase = GetUserProfileInfoUseCase()

    fun getUserProfileData() {
        viewModelScope.launch {
            uiStateEmitter.value = BaseUiState.loading
            val result = getUserProfileInfoUseCase.invoke()
            if (result == null) {
                uiStateEmitter.value = BaseUiState.Error(
                    EntityException.Local(
                        LocalErrorCodes.USER_PROFILE_ERROR,
                        "AN ERROR OCURRED IN USER PROFILE GET ENDPOINT"
                    )
                )
                return@launch
            }
            val profileInfo = result?.let { it } ?: return@launch
            uiStateEmitter.value = BaseUiState.SuccessResult(profileInfo)
        }
    }
}