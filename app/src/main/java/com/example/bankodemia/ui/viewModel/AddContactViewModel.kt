package com.example.bankodemia.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.core.utils.idUserBodyKey
import com.example.bankodemia.core.utils.jsonFormat
import com.example.bankodemia.core.utils.shortNameBodyKey
import com.example.bankodemia.domain.useCase.CreateContactUseCase
import com.example.bankodemia.domain.useCase.UpdateContactIdUseCase
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class AddContactViewModel : BaseViewModel() {
    val updateContactIdUseCase = UpdateContactIdUseCase()
    val createContactUseCase = CreateContactUseCase()

    fun updateContact(id: String, shortName: String) {
        viewModelScope.launch {
            uiStateEmitter.value = BaseUiState.loading
            val contactUpdate = makeUpdateParameters(shortName)
            val result = updateContactIdUseCase.invoke(id, contactUpdate)
            if (result.second != null) {
                val error = result.let { it.second?.let { it } } ?: return@launch
                uiStateEmitter.value = BaseUiState.Error(error)
            }
            val responseContactUpdate =
                result.let { it.first?.let { it } } ?: return@launch
            uiStateEmitter.value = BaseUiState.SuccessResult(responseContactUpdate)
        }
    }

    private fun makeUpdateParameters(
        shortName: String
    ): RequestBody {
        val contactUpdate = JSONObject()
        contactUpdate.put(shortNameBodyKey, shortName)
        return RequestBody.create(MediaType.parse(jsonFormat), contactUpdate.toString())
    }

    fun createContact(fullName: String,idUser: String) {
        viewModelScope.launch {
            uiStateEmitter.value = BaseUiState.loading
            val createContact = makeCreateParameters(fullName,idUser)
            val result = createContactUseCase.invoke(createContact)
            if (result.second != null) {
                val error = result.let { it.second?.let { it } } ?: return@launch
                uiStateEmitter.value = BaseUiState.Error(error)
            }
            val responseContactUpdate =
                result.let { it.first?.let { it } } ?: return@launch
            uiStateEmitter.value = BaseUiState.SuccessResult(responseContactUpdate)
        }
    }

    private fun makeCreateParameters(
        fullName: String,
        idUser:String
    ): RequestBody {
        val contactCreate = JSONObject()
        contactCreate.put(shortNameBodyKey, fullName)
        contactCreate.put(idUserBodyKey, idUser)
        return RequestBody.create(MediaType.parse(jsonFormat), contactCreate.toString())
    }
}