package com.example.bankodemia.Model

import android.media.MediaDrm

data class BadRequestApiError(
    val statusCode: MediaDrm.ErrorCodes,
    val message: List<String>,
    val error: String
)
