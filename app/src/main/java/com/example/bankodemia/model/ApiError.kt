package com.example.bankodemia.model

import com.example.bankodemia.core.ErrorCodes

data class ApiError(
    val statusCode: ErrorCodes,
    val messaage: String,
    val error: String?
)
