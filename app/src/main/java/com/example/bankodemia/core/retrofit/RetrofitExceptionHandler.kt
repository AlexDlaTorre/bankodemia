package com.example.bankodemia.core.retrofit

import com.example.bankodemia.core.empty
import com.example.bankodemia.core.utils.HttpUrlConnection
import com.example.bankodemia.data.model.EntityException
import retrofit2.Response

interface RetrofitExceptionHandler {
    fun <R, ERM> createApiExeption(
        response: Response<R>,
        errorResponseModel: Class<ERM>
    ): EntityException.Api {
        val body: String? = if (response.isSuccessful) {
            response.raw().body()?.toString()
        } else {
            response.errorBody()?.source()?.buffer?.clone()?.readUtf8()
        }
        val statusCode: Int = response.code()
        val url: String = response.raw().request().url().toString()
        return when (statusCode) {
            HttpUrlConnection.HTTP_GATEWAY_TIMEOUT -> EntityException.Api.Timeout(
                String.empty, statusCode, url
            )
            HttpUrlConnection.HTTP_UNAUTHORIZED -> EntityException.Api.Unauthorized(
                String.empty, statusCode, url
            )
            HttpUrlConnection.HTTP_UNAVAILABLE -> EntityException.Api.Unavailable(
                String.empty, statusCode, url
            )
            else -> {
                return EntityException.Api.General(
                    "Response body or response error is empty",
                    statusCode,
                    url
                )
            }
        }
    }
}