package com.example.bankodemia.domain.useCase

import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.data.repository.TransactionRepository
import com.example.bankodemia.domain.domainObjects.Transaction.TransactionPostReponseDTO
import okhttp3.RequestBody

class MakePaymentUseCase {
    val repository = TransactionRepository()

    suspend operator fun invoke(parameters: RequestBody): Pair<TransactionPostReponseDTO?, BankodemiaError?> = repository.makeTransaction(parameters)
}