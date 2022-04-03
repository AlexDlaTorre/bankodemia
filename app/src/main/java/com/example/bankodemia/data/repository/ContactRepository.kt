package com.example.bankodemia.data.repository

import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.domain.domainObjects.Contact.ContactDTO
import com.example.bankodemia.domain.domainObjects.Contact.ContactGetDTO
import com.example.bankodemia.domain.domainObjects.Contact.ContactPostDTO
import com.example.bankodemia.network.service.ContactServiceNetwork
import okhttp3.RequestBody

class ContactRepository {
    private val api = ContactServiceNetwork()

    suspend fun getContactListInfo(): Pair<ContactGetDTO?, BankodemiaError?> {
        val response = api.getContactList()
        val contactsDTO = response?.let { it.first?.let { contact -> ContactGetDTO(contact) } }
        return contactsDTO to response.second
    }

    suspend fun deleteContactInfo(id: String): Pair<ContactPostDTO?, BankodemiaError?> {
        val response = api.deleteContactInfo(id)
        println("GETCONTACT3")
        val idContact = response?.let { it.first?.let { contactId -> ContactPostDTO(contactId) } }
        return idContact to response.second
    }

    suspend fun upDateContactInfo(id: String,parametersUdate: RequestBody): Pair<ContactPostDTO?, BankodemiaError?> {
        val response = api.updateContactInfo(id, parametersUdate)
        println("GETCONTACT3")
        val idContact = response?.let { it.first?.let { contact -> ContactPostDTO(contact) } }
        return idContact to response.second
    }
}