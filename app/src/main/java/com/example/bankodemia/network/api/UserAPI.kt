package com.example.bankodemia.network.api

import com.example.bankodemia.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserAPI {
    @GET("/users/me/profile")
    // TODO- remove harcoded token when logic is available
    suspend fun getUserProfileInfo(@Header("Authorization") token: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjJiYjEzNDhjZTZjNDc4ZDBlMWJmZTYiLCJpYXQiOjE2NDgxODIwNTcsImV4cCI6MTY0ODE4NTY1N30.TeqgOSAhMQHIIXzQ5j5FbvEtGVKJ3v5oUXHqZ2zlhlt"): Response<User.UserProfile>
}