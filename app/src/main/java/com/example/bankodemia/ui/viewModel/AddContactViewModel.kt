package com.example.bankodemia.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankodemia.core.types.MovementType
import com.example.bankodemia.core.utils.*
import com.example.bankodemia.domain.useCase.DeleteContactIdUseCase
import com.example.bankodemia.domain.useCase.GetContactsListUseCase
import com.example.bankodemia.domain.useCase.UpdateContactIdUseCase
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class AddContactViewModel : BaseViewModel() {
    val updateContactIdUseCase = UpdateContactIdUseCase()

    fun updateContact(id: String,shortName: String) {
        viewModelScope.launch {
            uiStateEmitter.value = BaseUiState.loading
            val parametersUdate = makeUpdateParameters(id,shortName)
            val result = updateContactIdUseCase.invoke(id, parametersUdate)
            if (result.second != null) {
                val error = result?.let { it.second?.let { it } } ?: return@launch
                uiStateEmitter.value = BaseUiState.Error(error)
            }
            val responseContactUpdate = result?.let { it.first?.let { it } } ?: return@launch ?: return@launch
            uiStateEmitter.value = BaseUiState.SuccessResult(responseContactUpdate)
            println("GETCONTACT2")
        }
    }

    private fun makeUpdateParameters(id: String,
                                      shortName: String): RequestBody {
        val contactUpdate = JSONObject()
        contactUpdate.put(amountBodyKey, id)
        contactUpdate.put(typeBodyKey, shortName)
        return RequestBody.create(MediaType.parse(jsonFormat), contactUpdate.toString())
    }
}