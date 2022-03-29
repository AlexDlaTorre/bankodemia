package com.example.bankodemia.network.service

import android.util.Log
import com.example.bankodemia.core.instances.RetrofitBankodemiaInstance
import com.example.bankodemia.core.utils.createApiError
import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.data.model.BankodemiaErrorResponse
import com.example.bankodemia.data.model.Transaction
import com.example.bankodemia.network.api.TransactionsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

class TransactionServiceNetwork {
    private val retrofit = RetrofitBankodemiaInstance.getRetrofit().create(TransactionsApi::class.java)

    suspend fun makeTransaction(parameters: RequestBody): Pair<Transaction.PostResponse?, BankodemiaError?> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.makeTransaction(parameters)
            Log.d("PostTransactionsResponse", response.body().toString())
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