package com.example.bankodemia.domain.domainObjects.Contact

import com.example.bankodemia.model.Contact

data class ContactPostDataDTO(val response: Contact.PostData) {
    val contact: ContactDTO

    init {
        contact = ContactDTO(response.contact)
    }
}
