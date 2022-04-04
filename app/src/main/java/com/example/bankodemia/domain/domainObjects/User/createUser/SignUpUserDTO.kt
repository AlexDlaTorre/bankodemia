package com.example.bankodemia.domain.domainObjects.User.createUser

import com.example.bankodemia.core.types.IdentityType
import com.example.bankodemia.data.model.User

data class SignUpUserDTO(val user: User.SignUpUser) {
    val id: String
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
    val v: Int

    init {
        id = user.id
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
        v = user.v
    }
}
