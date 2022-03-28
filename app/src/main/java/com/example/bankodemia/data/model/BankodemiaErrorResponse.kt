package com.example.bankodemia.data.model

import com.google.gson.annotations.SerializedName

data class BankodemiaError(
    val code: Int,
    val messages: List<String>?,
    val message: String?,
    val error: String
)

data class BankodemiaErrorResponse(
    val statusCode: Int,
    val message: String?,
    val error: String
)