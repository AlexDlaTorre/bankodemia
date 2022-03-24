package com.example.bankodemia.network.service

import android.util.Log
import com.example.bankodemia.core.RetrofitBankodemiaInstance
import com.example.bankodemia.core.retrofit.RetrofitExceptionHandler
import com.example.bankodemia.data.model.BankodemiaErrorResponse
import com.example.bankodemia.data.model.User
import com.example.bankodemia.network.api.UserAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserServiceNetwork {
    private val retrofit = RetrofitBankodemiaInstance.getRetrofit().create(UserAPI::class.java)
    private lateinit var exceptionHandler: RetrofitExceptionHandler

    suspend fun getUserProfileInfo(): User.UserProfile? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.getUserProfileInfo()
            Log.d("UserProfileResponse", response.body().toString())
            val responseBody = response.body() ?: throw exceptionHandler.createApiExeption(response, BankodemiaErrorResponse::class.java)
            responseBody
        }
    }

    suspend fun getUsers(): User.GetResponse? {
        return withContext(Dispatchers.IO) {
            // TODO - remove harcoded token when logic is available
            val response = retrofit.getUsers("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjJiYjEzNDhjZTZjNDc4ZDBlMWJmZTYiLCJpYXQiOjE2NDc1NzE4MDMsImV4cCI6MTY0NzU3NTQwM30.HAiuhoIGzTR9u6QMT89WZFfWqyF5M3nUmawod4ijOFY")
            val responseBody = response.body() ?: throw exceptionHandler.createApiExeption(response, BankodemiaErrorResponse::class.java)
            responseBody
        }
    }
}