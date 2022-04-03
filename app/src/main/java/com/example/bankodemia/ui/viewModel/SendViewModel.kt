package com.example.bankodemia.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.example.bankodemia.core.utils.*
import com.example.bankodemia.domain.domainObjects.Contact.ContactDTO
import com.example.bankodemia.domain.useCase.DeleteContactIdUseCase
import com.example.bankodemia.domain.useCase.GetContactsListUseCase
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class SendViewModel : BaseViewModel() {
    val getContactsListUseCase = GetContactsListUseCase()
    val deleteContactIdUseCase = DeleteContactIdUseCase()

    fun getContactsListData() {
        viewModelScope.launch {
            uiStateEmitter.value = BaseUiState.loading
            val result = getContactsListUseCase.invoke()
            if (result.second != null) {
                val error = result?.let { it.second?.let { it } } ?: return@launch
                uiStateEmitter.value = BaseUiState.Error(error)
            }
            val response = result?.let { it.first?.let { it } } ?: return@launch ?: return@launch
            uiStateEmitter.value = BaseUiState.SuccessResult(response)
        }
    }

    fun deleteContact(id: String) {
        viewModelScope.launch {
            uiStateEmitter.value = BaseUiState.loading
            val idContact = getContactId(id)
            val result = deleteContactIdUseCase.invoke(idContact)
            if (result.second != null) {
                val error = result?.let { it.second?.let { it } } ?: return@launch
                uiStateEmitter.value = BaseUiState.Error(error)
            }
            val responseContactDelete = result?.let { it.first?.let { it } } ?: return@launch ?: return@launch
            uiStateEmitter.value = BaseUiState.SuccessResult(responseContactDelete)
            println("GETCONTACT2")
        }
    }

    fun getContact(contact: ContactDTO) {
        deleteContact(contact.owner.id)
        println("GETCONTACT")
    }

    private fun getContactId(id: String):RequestBody {
        val idContact = JSONObject()
        idContact.put(idBodyKey, id)
        return RequestBody.create(MediaType.parse(jsonFormat), idContact.toString())
    }


}