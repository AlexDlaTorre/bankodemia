package com.example.bankodemia.domain.domainObjects.Contact

import com.example.bankodemia.model.Contact

data class ContactGetDataDTO(val response: Contact.GetData) {
    val contacts: List<ContactDTO>

    init {
        contacts = response.contacts.map { ContactDTO(it) }
    }
}
