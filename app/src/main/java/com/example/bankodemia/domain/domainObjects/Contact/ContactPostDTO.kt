package com.example.bankodemia.domain.domainObjects.Contact

import com.example.bankodemia.data.model.Contact

data class ContactPostDTO(val response: Contact.PostResponse) {
    val success: Boolean
    val data: ContactPostDataDTO

    init {
        success = response.success
        data = ContactPostDataDTO(response.data)
    }
}
