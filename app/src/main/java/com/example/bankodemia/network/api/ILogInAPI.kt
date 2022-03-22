package com.example.bankodemia.network.api

import com.example.bankodemia.data.model.Auth
import retrofit2.Response
import retrofit2.http.*

interface ILogInAPI {

    @Headers("Content-Type: application/json")
    @POST("/auth/login")
    suspend fun logIn(@Body login: Auth.AuthLogIn): Response<Auth.AuthResponse>

}