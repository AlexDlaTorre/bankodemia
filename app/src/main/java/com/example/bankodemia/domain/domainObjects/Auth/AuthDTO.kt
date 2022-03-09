package com.example.bankodemia.domain.domainObjects.Auth

import com.example.bankodemia.model.Auth

class AuthDTO(val response: Auth.AuthResponse) {
    val token: String
    val expiresIn: String

    init {
        token = response.token
        expiresIn = response.expiresIn
    }
}