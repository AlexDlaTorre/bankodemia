package com.example.bankodemia.domain.domainObjects.User

import com.example.bankodemia.core.empty
import com.example.bankodemia.core.types.IdentityType
import com.example.bankodemia.core.whiteSpace
import com.example.bankodemia.data.model.User

data class UserDTO(val user: User.User) {
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

