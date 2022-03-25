package com.example.bankodemia.network.service

import android.util.Log
import com.example.bankodemia.core.instances.RetrofitBankodemiaInstance
import com.example.bankodemia.data.model.Auth
import com.example.bankodemia.network.api.ILogInAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LogInServiceNetwork {
    private val retrofit = RetrofitBankodemiaInstance.getRetrofit().create(ILogInAPI::class.java)

    suspend fun logIn(login: Auth.AuthLogIn): Auth.AuthResponse? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.logIn(login)
            response.body()
        }
    }

}