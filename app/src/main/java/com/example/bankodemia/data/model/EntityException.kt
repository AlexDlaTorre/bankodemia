package com.example.bankodemia.data.model

import com.example.bankodemia.core.empty
import com.example.bankodemia.core.hypen
import java.lang.Exception

sealed class EntityException(
    val errorCode: String,
    val techinicalMessage: String,
    val friendlyMessage: String
): Exception(errorCode + String.hypen + techinicalMessage) {

    class Local(
        errorCode: String,
        technicalMessage: String?,
        messages: List<String>?,
        error: String
    )
}

