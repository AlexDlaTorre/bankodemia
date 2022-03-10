package com.example.bankodemia.model

class Auth {
    init { }
    data class AuthResponse(
        val token: String,
        val expiresIn: String
    )

}