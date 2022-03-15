package com.example.bankodemia.network.api

import com.example.bankodemia.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserAPI {
    @GET("/users/me/profile")
    // TODO- remove harcoded token when logic is available
    suspend fun getUserProfileInfo(@Header("Authorization") token: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjJiYjEzNDhjZTZjNDc4ZDBlMWJmZTYiLCJpYXQiOjE2NDczMTE0MDYsImV4cCI6MTY0NzMxNTAwNn0.RB5gr-_yFKCrtyyARUjPSoU4j7BIASc5HMFkUd8rPMo"): Response<User.UserProfile>
}