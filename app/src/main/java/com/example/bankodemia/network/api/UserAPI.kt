package com.example.bankodemia.network.api

import com.example.bankodemia.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UserAPI {
    @GET("/users/me/profile")
    // TODO- remove harcoded token when logic is available
    suspend fun getUserProfileInfo(@Header("Authorization") token: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjJiYjEzNDhjZTZjNDc4ZDBlMWJmZTYiLCJpYXQiOjE2NDc3MTMxNDMsImV4cCI6MTY0NzcxNjc0M30.7o6XZ4B0xI3LhWWEPdFcKkz6Ejo_vOj0gYBvKu7H46A"): Response<User.UserProfile>
    suspend fun getUsers(@Query("query") query: String): Response<User.GetResponse>
}