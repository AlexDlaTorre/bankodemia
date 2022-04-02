package com.example.bankodemia.domain.domainObjects.Contact

import com.example.bankodemia.domain.domainObjects.User.UserDTO
import com.example.bankodemia.data.model.Contact

data class ContactDeleteDTO(val response: Contact.Contact): java.io.Serializable {
    val shortName: String
    val owner: UserDTO
    val user: UserDTO

    init {
        shortName = response.shortName
        owner = UserDTO(response.owner)
        user = UserDTO(response.user)
    }
}
