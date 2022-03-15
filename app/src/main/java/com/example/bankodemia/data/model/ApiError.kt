package com.example.bankodemia.data.model

import com.example.bankodemia.core.types.ErrorCodes

data class ApiError(
    val statusCode: ErrorCodes,
    val messaage: String,
    val error: String?
)
