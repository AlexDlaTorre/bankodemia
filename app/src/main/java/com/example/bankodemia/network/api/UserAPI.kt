package com.example.bankodemia.network.api

import com.example.bankodemia.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserAPI {
    @GET("/users/me/profile")
    // TODO- remove harcoded token when logic is available
    suspend fun getUserProfileInfo(@Header("Authorization") token: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjJiYjEzNDhjZTZjNDc4ZDBlMWJmZTYiLCJpYXQiOjE2NDc0NjQ3NzIsImV4cCI6MTY0NzQ2ODM3Mn0.xR0pahDulp_Ad0yCodZgIuljpzKDoc_XS8iJYiofMa4"): Response<User.UserProfile>
}