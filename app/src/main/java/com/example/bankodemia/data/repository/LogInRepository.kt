package com.example.bankodemia.data.repository

import android.util.Log
import com.example.bankodemia.data.model.Auth
import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.domain.domainObjects.Auth.AuthDTO
import com.example.bankodemia.network.service.LogInServiceNetwork

class LogInRepository {
    private val api = LogInServiceNetwork()

    suspend fun logIn(login : Auth.AuthLogIn): Pair<AuthDTO?, BankodemiaError?> {
        val response = api.logIn(login)
        Log.d("LogInRepository", response.toString())
        val authDTO = response?.let { it.first?.let{ login -> AuthDTO(login)} }
        return authDTO to response.second
    }
}