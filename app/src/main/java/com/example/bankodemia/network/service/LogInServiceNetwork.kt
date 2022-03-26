package com.example.bankodemia.network.service

import android.util.Log
import com.example.bankodemia.core.instances.RetrofitBankodemiaInstance
import com.example.bankodemia.core.utils.createApiError
import com.example.bankodemia.data.model.Auth
import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.data.model.BankodemiaErrorResponse
import com.example.bankodemia.data.model.User
import com.example.bankodemia.network.api.ILogInAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LogInServiceNetwork {
    private val retrofit = RetrofitBankodemiaInstance.getRetrofit().create(ILogInAPI::class.java)

    suspend fun logIn(login: Auth.AuthLogIn): Pair<Auth.AuthResponse?, BankodemiaError?> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.logIn(login)
            Log.d("LoginProfileResponse", response.body().toString())

            val responseBody = response.body()
            if (responseBody != null) {
                responseBody to null
            } else {
                val errorResponse = createApiError(response, BankodemiaErrorResponse::class.java)
                null to errorResponse
            }
        }
    }

}