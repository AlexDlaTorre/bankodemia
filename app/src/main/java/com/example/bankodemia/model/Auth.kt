package com.example.bankodemia.model

object Auth {
    init { }
    data class Response(
        val token: String,
        val expiresIn: String
    )
}