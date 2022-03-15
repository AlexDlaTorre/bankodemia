package com.example.bankodemia.data.model

import com.example.bankodemia.core.empty
import com.example.bankodemia.core.hypen
import java.lang.Exception

sealed class EntityException(
    val errorCode: String,
    val techinicalMessage: String,
    val friendlyMessage: String
): Exception(errorCode + String.hypen + techinicalMessage) {

    sealed class Api(
        errorCode: String,
        technicalMessage: String,
        friendlyMessage: String,
        val url: String
    ): EntityException(errorCode, technicalMessage, friendlyMessage) {

        class General(
            techinicalMessage: String,
            statusCode: Int,
            url: String): Api(
            statusCode.toString(),
            techinicalMessage,
            String.empty,
            url
        )

        class Timeout(
            techinicalMessage: String,
            statusCode: Int,
            url: String): Api(
            statusCode.toString(),
            techinicalMessage,
            String.empty,
            url
            )

        class Unauthorized(
            techinicalMessage: String,
            statusCode: Int,
            url: String): Api(
            statusCode.toString(),
            techinicalMessage,
            String.empty,
            url
        )

        class Unavailable(
            techinicalMessage: String,
            statusCode: Int,
            url: String): Api(
            statusCode.toString(),
            techinicalMessage,
            String.empty,
            url
        )
    }

    class Local(
        errorCode: String,
        technicalMessage: String,
        friendlyMessage: String = String.empty
    ): EntityException(errorCode, technicalMessage, friendlyMessage)
}

