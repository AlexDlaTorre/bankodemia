package com.example.bankodemia.data.repository

import com.example.bankodemia.domain.domainObjects.Transaction.TransactionPostReponseDTO
import com.example.bankodemia.network.service.TransactionServiceNetwork
import okhttp3.RequestBody

class TransactionRepository {
    private val api = TransactionServiceNetwork()

    suspend fun makeTransaction(parameters: RequestBody): TransactionPostReponseDTO? {
        val response = api.makeDeposit(parameters)
        val transactionDTO = response?.let { TransactionPostReponseDTO(it) }
        return transactionDTO
    }
}