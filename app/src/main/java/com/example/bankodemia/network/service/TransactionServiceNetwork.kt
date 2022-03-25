package com.example.bankodemia.network.service

import android.util.Log
import com.example.bankodemia.core.instances.RetrofitBankodemiaInstance
import com.example.bankodemia.core.retrofit.RetrofitExceptionHandler
import com.example.bankodemia.data.model.BankodemiaErrorResponse
import com.example.bankodemia.data.model.Transaction
import com.example.bankodemia.network.api.TransactionsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

class TransactionServiceNetwork {
    private val retrofit =
        RetrofitBankodemiaInstance.getRetrofit().create(TransactionsApi::class.java)
    private lateinit var exceptionHandler: RetrofitExceptionHandler

    suspend fun makeDeposit(parameters: RequestBody): Transaction.PostResponse? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.makeTransaction(parameters)
            Log.d("PostTransactionsResponse", response.body().toString())
            val responseBody = response.body() ?: throw exceptionHandler.createApiExeption(
                response,
                BankodemiaErrorResponse::class.java
            )
            responseBody
        }
    }
}