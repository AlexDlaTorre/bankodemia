package com.example.bankodemia.network.service

import android.util.Log
import com.example.bankodemia.core.instances.RetrofitBankodemiaInstance
import com.example.bankodemia.core.utils.createApiError
import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.data.model.BankodemiaErrorResponse
import com.example.bankodemia.data.model.User
import com.example.bankodemia.network.api.UserAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserServiceNetwork {
    private val retrofit = RetrofitBankodemiaInstance.getRetrofit().create(UserAPI::class.java)

    suspend fun getUserProfileInfo(): Pair<User.UserProfile?, BankodemiaError?> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.getUserProfileInfo()
            Log.d("UserProfileResponse", response.body().toString())

            val responseBody = response.body()
            if (responseBody != null) {
                responseBody to null
            } else {
                val errorResponse = createApiError(response, BankodemiaErrorResponse::class.java)
                null to errorResponse
            }
        }
    }
}