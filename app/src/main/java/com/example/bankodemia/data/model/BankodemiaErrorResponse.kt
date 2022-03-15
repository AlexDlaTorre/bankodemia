package com.example.bankodemia.data.model

import com.google.gson.annotations.SerializedName

data class BankodemiaErrorResponse(
    val code: Int,
    @SerializedName("message")
    val messages: List<String>?,
    val message: String?,
    val error: String
)
