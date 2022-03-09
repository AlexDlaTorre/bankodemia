package com.example.bankodemia.domain.domainObjects.Contact

import com.example.bankodemia.domain.domainObjects.User.UserDTO
import com.example.bankodemia.model.Contact

data class ContactDTO(val response: Contact.Contact) {
    val shortName: String
    val owner: UserDTO
    val user: UserDTO

    init {
        shortName = response.shortName
        owner = UserDTO(response.owner)
        user = UserDTO(response.user)
    }
}
