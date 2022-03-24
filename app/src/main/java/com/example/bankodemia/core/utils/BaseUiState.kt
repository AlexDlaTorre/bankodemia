package com.example.bankodemia.core.utils

import com.example.bankodemia.core.empty

sealed class BaseUiState {
    object loading: BaseUiState()

    class LoadingWithMessage(val message: String): BaseUiState()

    open class SuccessResult<T>(var result: T): BaseUiState() {
        override fun equals(other: Any?): Boolean {
            if (this == other) return true
            if (javaClass != other?.javaClass) return false
            other as SuccessResult<*>

            if(result != other.result) return false

            return true
        }
    }

    class Error(val error: Throwable): BaseUiState() {
        data class UIError(
            val code: String = "UNEXPECTED ERROR CODE",
            val message: String = String.empty,
            val eventId: String? = String.empty
        ): BaseUiState()
    }
}