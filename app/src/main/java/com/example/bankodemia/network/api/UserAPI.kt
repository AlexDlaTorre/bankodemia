package com.example.bankodemia.network.api

import com.example.bankodemia.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserAPI {
    @GET("/users/me/profile")
    // TODO- remove harcoded token when logic is available
    suspend fun getUserProfileInfo(@Header("Authorization") token: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjJiYjEzNDhjZTZjNDc4ZDBlMWJmZTYiLCJpYXQiOjE2NDc0NzgzMjcsImV4cCI6MTY0NzQ4MTkyN30.EuaMp6S1WMugIU-MS-AQ7gvAmJW0QBmig4hp6fNe0T8"): Response<User.UserProfile>
}