package com.example.bankodemia.Model

object Auth {
    init { }
    data class Response(
        val token: String,
        val expiresIn: String
    )
}