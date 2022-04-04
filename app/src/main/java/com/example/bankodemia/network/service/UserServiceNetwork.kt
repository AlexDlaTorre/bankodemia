package com.example.bankodemia.network.service

import android.util.Log
import com.example.bankodemia.core.Extensions.TAG
import com.example.bankodemia.core.instances.RetrofitBankodemiaInstance
import com.example.bankodemia.core.utils.createApiError
import com.example.bankodemia.data.model.*
import com.example.bankodemia.network.api.UserAPI
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.net.ssl.HttpsURLConnection

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

    suspend fun getUsers(query: String): Pair<User.GetResponse?, BankodemiaError?> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.getUsers(query)
            val responseBody = response.body()
            if (responseBody != null) {
                responseBody to null
            } else {
                val errorResponse = createApiError(response, BankodemiaErrorResponse::class.java)
                null to errorResponse
            }
        }
    }

    suspend fun createUserAccount(user: User.SingUpCreateUser): Pair<User.PostResponse?, BankodemiaError?> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.createUserAccount(user)
            Log.d("createUserAccount", response.body().toString())
            val responseBody = response.body()

            if (responseBody != null) {
                responseBody to null
            }else if (response.code() == HttpsURLConnection.HTTP_PRECON_FAILED) {
                val errorResponse = createApiError(response, BankodemiaErrorPreconFailed::class.java)
                null to errorResponse
            }else if (response.code() == HttpsURLConnection.HTTP_BAD_REQUEST){
                val errorResponse = createApiError(response, BankodemiaErrorBadRequest::class.java)
                null to errorResponse
            }
            else{
                val errorResponse = createApiError(response, BankodemiaErrorResponse::class.java)
                null to errorResponse
            }

        }
    }
}