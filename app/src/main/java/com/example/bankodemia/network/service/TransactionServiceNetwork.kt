package com.example.bankodemia.network.service

import android.util.Log
import com.example.bankodemia.core.RetrofitBankodemiaInstance
import com.example.bankodemia.core.retrofit.RetrofitExceptionHandler
import com.example.bankodemia.data.model.BankodemiaErrorResponse
import com.example.bankodemia.data.model.Transaction
import com.example.bankodemia.network.api.TransactionsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

class TransactionServiceNetwork {
    private val retrofit = RetrofitBankodemiaInstance.getRetrofit().create(TransactionsApi::class.java)
    private lateinit var exceptionHandler: RetrofitExceptionHandler

    suspend fun makeDeposit(parameters: RequestBody): Transaction.PostResponse? {
        return withContext(Dispatchers.IO) {
            // TODO - remove hardcoded token
            val response = retrofit.makeTransaction("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjJiYjEzNDhjZTZjNDc4ZDBlMWJmZTYiLCJpYXQiOjE2NDc1NzE4MDMsImV4cCI6MTY0NzU3NTQwM30.HAiuhoIGzTR9u6QMT89WZFfWqyF5M3nUmawod4ijOFY",
            parameters)
            Log.d("PostTransactionsResponse", response.body().toString())
            val responseBody = response.body() ?: throw exceptionHandler.createApiExeption(response, BankodemiaErrorResponse::class.java)
            responseBody
        }
    }
}