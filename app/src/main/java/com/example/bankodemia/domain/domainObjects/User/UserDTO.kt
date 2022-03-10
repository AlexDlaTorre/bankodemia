package com.example.bankodemia.domain.domainObjects.User

import com.example.bankodemia.core.IdentityType
import com.example.bankodemia.model.User

data class UserDTO(val user: User.User) {
    val email: String
    val name: String
    val lastName: String
    val occupation: String
    val birthDate: String
    val password: String
    val phone: String
    val isPhoneVerified: Boolean
    val identityImage: String
    val identityImageType: IdentityType

    init {
        email = user.email
        name = user.name
        lastName = user.lastName
        occupation = user.occupation
        birthDate = user.birthDate
        password = user.password
        phone = user.phone
        isPhoneVerified = user.isPhoneVerified
        identityImage = user.identityImage
        identityImageType = user.identityImageType
    }
}

