package com.example.bankodemia.domain.domainObjects.Transaction

import com.example.bankodemia.domain.domainObjects.User.UserDTO
import com.example.bankodemia.model.Transaction

data class TransactionDTO(val response: Transaction.Transaction){
    val amount: Double
    val type: String
    val concept: String
    val createdAt: String
    val issuer: UserDTO
    val destinationUser: UserDTO
    val isIncome: Boolean

    init {
        amount = response.amount
        type = response.type.value
        concept = response.concept
        createdAt = response.createdAt
        issuer = UserDTO(response.issuer)
        destinationUser = UserDTO(response.destinationUser)
        isIncome = response.isIncome
    }
}
