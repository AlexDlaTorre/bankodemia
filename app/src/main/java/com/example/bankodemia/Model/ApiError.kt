package com.example.bankodemia.Model

import com.example.bankodemia.Core.ErrorCodes

data class ApiError(
    val statusCode: ErrorCodes,
    val messaage: String,
    val error: String?
)
