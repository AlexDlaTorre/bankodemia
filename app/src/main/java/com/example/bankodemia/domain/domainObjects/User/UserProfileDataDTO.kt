package com.example.bankodemia.domain.domainObjects.User

import com.example.bankodemia.domain.domainObjects.Transaction.TransactionDTO
import com.example.bankodemia.model.User

data class UserProfileDataDTO(val response: User.ProfileData) {
    val user: UserDTO
    val transactions: List<TransactionDTO>

    init {
        user = UserDTO(response.user)
        transactions = response.transactions.map { TransactionDTO(it) }
    }
}
