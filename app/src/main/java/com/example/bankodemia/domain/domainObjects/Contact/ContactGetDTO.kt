package com.example.bankodemia.domain.domainObjects.Contact

import com.example.bankodemia.data.model.Contact

class ContactGetDTO(val response: Contact.GetResponse) {
    val success: Boolean
    val data: ContactGetDataDTO

    init {
        success = response.success
        data = ContactGetDataDTO(response.data)
    }
}