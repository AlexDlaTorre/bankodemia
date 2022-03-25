package com.example.bankodemia.core.utils

import com.example.bankodemia.core.empty
import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.data.model.EntityException

sealed class BaseUiState {
    object loading: BaseUiState()

    class LoadingWithMessage(val message: String): BaseUiState()

    open class Success: BaseUiState()

    open class SuccessResult<T>(var result: T): BaseUiState() {
        override fun equals(other: Any?): Boolean {
            if (this == other) return true
            if (javaClass != other?.javaClass) return false
            other as SuccessResult<*>

            if(result != other.result) return false

            return true
        }
    }

    data class Error(val errorResponse: BankodemiaError): BaseUiState() {
        val errorCode: String
        val message: String?
        val messages: List<String>?
        val error: String

        init {
            errorCode = errorResponse.code.toString()
            message = errorResponse.message
            messages = errorResponse.messages
            error = errorResponse.error
        }
    }
}