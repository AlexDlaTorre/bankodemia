package com.example.bankodemia.data.repository

import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.domain.domainObjects.Contact.ContactGetDTO
import com.example.bankodemia.domain.domainObjects.User.geUserProfile.UserProfileDTO
import com.example.bankodemia.network.service.ContactServiceNetwork
import com.example.bankodemia.network.service.UserServiceNetwork

class ContactRepository {
    private val api = ContactServiceNetwork()

    suspend fun getContactListInfo(): Pair<ContactGetDTO?, BankodemiaError?> {
        val response = api.getContactList()
        val contactsDTO = response?.let { it.first?.let { contact -> ContactGetDTO(contact) } }
        return contactsDTO to response.second
    }
}