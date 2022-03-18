package com.example.bankodemia.domain.domainObjects.Transaction

import com.example.bankodemia.core.zero
import com.example.bankodemia.data.model.Transaction

data class TransactionPostDataDTO(val response: Transaction.PostTransactionData) {
    val transaction: TransactionDTO
    val finalBalance: Int

    init {
        transaction = TransactionDTO(response.transaction)
        finalBalance = response.finalBalance ?: Int.zero
    }
}
