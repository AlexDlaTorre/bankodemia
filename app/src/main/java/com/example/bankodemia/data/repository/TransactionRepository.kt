package com.example.bankodemia.data.repository

import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.domain.domainObjects.Transaction.TransactionPostReponseDTO
import com.example.bankodemia.network.service.TransactionServiceNetwork
import okhttp3.RequestBody

class TransactionRepository {
    private val api = TransactionServiceNetwork()

    suspend fun makeTransaction(parameters: RequestBody): Pair<TransactionPostReponseDTO?, BankodemiaError?> {
        val response = api.makeTransaction(parameters)
        val transactionDTO = response?.let { it.first?.let { transaction -> TransactionPostReponseDTO(transaction) } }
        return transactionDTO to response.second
    }
}