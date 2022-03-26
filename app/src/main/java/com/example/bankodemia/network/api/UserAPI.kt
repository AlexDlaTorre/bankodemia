package com.example.bankodemia.network.api

import com.example.bankodemia.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UserAPI {
    @GET("/users/me/profile")
    suspend fun getUserProfileInfo(): Response<User.UserProfile>
    suspend fun getUsers(@Query("query") query: String): Response<User.GetResponse>
}