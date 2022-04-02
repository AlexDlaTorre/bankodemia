package com.example.bankodemia.core.utils

import com.example.bankodemia.core.empty
import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.data.model.BankodemiaErrorResponse
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection

// Keys used to pass data
val TRANSACTIONDETAIL: String = "TransactionDetail"
val APP_ID: String = "com.example.bankodemia"
val CONTACTDATA: String = "ContactData"

// Body keys for post request
val amountBodyKey       = "amount"
val typeBodyKey         = "type"
val conceptBodyKey      = "concept"
val destinationUserKey  = "destinationUser"
val idBodyKey  = "id"
val nameBodyKey  = "name"

// json format
val jsonFormat = "application/json; charset=utf-8"

// self deposit description
val selfDeposit = "Deposito a mi cuenta"

// quotes to use in loader
val quotes: List<String> = listOf("Quienes creen que el dinero lo hace todo, terminan haciendo todo por dinero",
                                  "El amigo ha de ser como el dinero, que antes de necesitarle, se sabe el valor que tiene",
                                   "¿Qué es la riqueza? Nada, si no se gasta; nada, si se malgasta",
                                   "No pongas tu interés en el dinero, pero pon tu dinero al interés",
                                   "Abre tu puerta a la pereza y entrará a tu casa la pobreza")

// Api Error Description
val endPointRequestTimeOut = "El tiempo de respuesta del servidor se ha agotado"
val unauthorized = "No estas autorizado para esta operacion"
val general = "Ocurrio un problema con el servidor, por favor intentalo de nuevo"


fun <R, ERM> createApiError(
    response: Response<R>,
    errorResponseModel: Class<ERM>
): BankodemiaError {
    val body: String? = if (response.isSuccessful) {
        response.raw().body()?.string()
    } else {
        response.errorBody()?.source()?.buffer()?.clone()?.readUtf8()
    }
    val statusCode: Int = response.code()
    val url: String = response.raw().request().url().toString()
    return when (statusCode) {
        HttpsURLConnection.HTTP_UNAUTHORIZED -> {
            try {
                val errorResponse: BankodemiaErrorResponse = Gson().fromJson(body, BankodemiaErrorResponse::class.java)
                val message = errorResponse.message?.let { it } ?: return BankodemiaError(
                    statusCode,
                    null,
                    unauthorized,
                    String.empty
                )
                return BankodemiaError(statusCode,
                    null,
                    message,
                    errorResponse.error)
            } catch (e: JsonSyntaxException) {
                return BankodemiaError(
                    statusCode,
                    null,
                    unauthorized,
                    String.empty
                )
            }
        }
        HttpsURLConnection.HTTP_GATEWAY_TIMEOUT -> BankodemiaError(
            statusCode,
            null,
            endPointRequestTimeOut,
            String.empty
        )
        else -> {
            if (body == null) {
                return BankodemiaError(
                    statusCode,
                    null,
                    general,
                    String.empty
                )
            } else {
                try {
                    val errorResponse = Gson().fromJson<ERM>(body, errorResponseModel::class.java)
                    when (errorResponse) {
                        is BankodemiaErrorResponse -> {
                            return BankodemiaError(
                                statusCode,
                                null,
                                errorResponse.message,
                                errorResponse.error
                            )
                        }
                        else -> {
                            return BankodemiaError(
                                statusCode,
                                null,
                                general,
                                String.empty
                            )
                        }
                    }
                } catch (e: JsonSyntaxException) {
                    return BankodemiaError(
                        statusCode,
                        null,
                        general,
                        String.empty
                    )
                }
            }
        }
    }
}