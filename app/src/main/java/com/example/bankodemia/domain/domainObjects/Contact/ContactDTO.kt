package com.example.bankodemia.domain.domainObjects.Contact

import com.example.bankodemia.domain.domainObjects.User.UserDTO
import com.example.bankodemia.data.model.Contact

data class ContactDTO(val response: Contact.Contact): java.io.Serializable {
    val _id:String
    val shortName: String
    val owner: UserDTO
    val user: UserDTO

    init {
        _id = response._id
        shortName = response.shortName
        owner = UserDTO(response.owner)
        user = UserDTO(response.user)
    }
}
