package com.example.bankodemia.data.model

import com.example.bankodemia.core.types.MovementType
import com.google.gson.annotations.SerializedName

class Transaction {
    data class PostResponse(
        val success: Boolean,
        val data: PostTransactionData)

    data class PostTransactionData(
        val transaction: Transaction,
        val finalBalance: Int?
    )

    data class Transaction(
        @SerializedName("_id")
        val id: String,
        val amount: Int,
        val type: MovementType,
        val concept: String,
        @SerializedName("created_at")
        val createdAt: String,
        val issuer: User.User,
        val destinationUser: User.User,
        val isIncome: Boolean?
    )

    data class GetResponse(
        val success: Boolean,
        val data: GetTransactionData
    )

    data class GetTransactionData(
        val transactions: List<Transaction>
    )
}