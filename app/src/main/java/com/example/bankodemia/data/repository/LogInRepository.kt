package com.example.bankodemia.data.repository

import com.example.bankodemia.data.model.Auth
import com.example.bankodemia.domain.domainObjects.Auth.AuthDTO
import com.example.bankodemia.network.service.LogInServiceNetwork

class LogInRepository {
    private val api = LogInServiceNetwork()

    suspend fun logIn(login : Auth.AuthLogIn): AuthDTO?{
        val response = api.logIn(login)
        val authDTO = response?.let { AuthDTO(it) }
        return authDTO
    }
}