package com.example.bankodemia.data.model

import android.media.MediaDrm

data class BadRequestApiError(
    val statusCode: MediaDrm.ErrorCodes,
    val message: List<String>,
    val error: String
)
