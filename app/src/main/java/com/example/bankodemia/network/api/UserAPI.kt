package com.example.bankodemia.network.api

import com.example.bankodemia.data.model.User
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {
    @GET("/users/me/profile")
    suspend fun getUserProfileInfo(): Response<User.UserProfile>
    @GET("/users/search?")
    suspend fun getUsers(@Query("query") query: String): Response<User.GetResponse>

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("/users")
    suspend fun createUserAccount(@Body user: User.SingUpCreateUser): Response<User.PostResponse>
}