package com.example.bankodemia.data.model

class Auth {
    init { }
    data class AuthResponse(
        val token: String,
        val expiresIn: String
    )

}