package com.example.bankodemia.domain.domainObjects.Contact

import com.example.bankodemia.domain.domainObjects.User.UserDTO
import com.example.bankodemia.data.model.Contact

data class ContactIdDTO(val response: Contact.IdContact): java.io.Serializable {
    val id:String

    init {
        id = response.id

    }
}
