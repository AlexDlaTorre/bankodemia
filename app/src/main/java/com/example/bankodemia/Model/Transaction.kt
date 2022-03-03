package com.example.bankodemia.Model

import com.example.bankodemia.Core.MovementType
import kotlinx.serialization.SerialName

object Transaction {
    init{}
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
        @SerialName("created_at")
        val createdAt: String,
        val issuer: User,
        val destinationUser: User,
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