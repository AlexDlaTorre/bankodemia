package com.example.bankodemia.domain.useCase

import com.example.bankodemia.data.repository.TransactionRepository
import com.example.bankodemia.domain.domainObjects.Transaction.TransactionPostReponseDTO
import okhttp3.RequestBody

class MakeTransactionUseCase {
    private val repository = TransactionRepository()

    suspend operator fun invoke(parameters: RequestBody): TransactionPostReponseDTO? = repository.makeTransaction(parameters)
}