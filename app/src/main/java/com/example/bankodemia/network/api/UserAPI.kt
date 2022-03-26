package com.example.bankodemia.network.api

import com.example.bankodemia.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserAPI {
    @GET("/users/me/profile")
    // TODO- remove harcoded token when logic is available
    suspend fun getUserProfileInfo(@Header("Authorization") token: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjNlODUyMzg1NmRiYzNlMTE1Zjk0ZWMiLCJpYXQiOjE2NDgyNzk2MTcsImV4cCI6MTY0ODI4MzIxN30.h7bwHdQY8qHPb8Zo1SMPq4sx0-QxJuvUW3LPcQDVDCE"): Response<User.UserProfile>
}
//    @GET("/users/me/profile")
//    suspend fun getUserProfileInfo(): Response<User.UserProfile>
//}