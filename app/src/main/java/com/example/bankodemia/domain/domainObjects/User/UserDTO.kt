package com.example.bankodemia.domain.domainObjects.User

import com.example.bankodemia.core.empty
import com.example.bankodemia.core.types.IdentityType
import com.example.bankodemia.core.whiteSpace
import com.example.bankodemia.data.model.User
import kotlinx.serialization.Serializable

data class UserDTO(val user: User.User): java.io.Serializable {
    val id: String
    val email: String
    val name: String
    val lastName: String

    val fullName: String
    get() = name + String.whiteSpace + lastName

    init {
        id = user.id
        email = user.email
        name = user.name
        lastName = user.lastName
    }
}

