package com.example.bankodemia.model

import com.example.bankodemia.core.MovementType

class Transaction {
    data class PostResponse(
        val success: Boolean,
        val data: PostTransactionData,
        val finalBalance: Int?)

    data class PostTransactionData(
        val transaction: Transaction
    )

    data class Transaction(
        val amount: Double,
        val type: MovementType,
        val concept: String,
        val created_at: String,
        val issuer: User.User,
        val destinationUser: User.User,
        val isIncome: Boolean
    )

    data class GetResponse(
        val success: Boolean,
        val data: GetTransactionData
    )

    data class GetTransactionData(
        val transactions: List<Transaction>
    )
}