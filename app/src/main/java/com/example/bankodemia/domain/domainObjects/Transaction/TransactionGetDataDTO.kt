package com.example.bankodemia.domain.domainObjects.Transaction

import com.example.bankodemia.model.Transaction

data class TransactionGetDataDTO(val response: Transaction.GetTransactionData) {
    val transactions: List<TransactionDTO>

    init {
        transactions = response.transactions.map { TransactionDTO(it) }
    }
}
