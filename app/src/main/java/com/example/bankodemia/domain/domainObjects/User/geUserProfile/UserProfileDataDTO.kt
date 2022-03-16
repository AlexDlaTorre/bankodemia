package com.example.bankodemia.domain.domainObjects.User.geUserProfile

import com.example.bankodemia.core.zero
import com.example.bankodemia.data.model.User
import com.example.bankodemia.domain.domainObjects.User.UserDTO
import java.text.NumberFormat
import java.util.*

data class UserProfileDataDTO(val response: User.ProfileData) {
    val user: UserDTO
    val transactions: List<UserProfileTransactionDTO>
    val balance: Int

    val formattedBalance: String
    get() {
        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.maximumFractionDigits = Int.zero
        return "${numberFormat.format(balance.toDouble())}"
    }

    init {
        user = UserDTO(response.user)
        transactions = response.transactions.map { UserProfileTransactionDTO(it) }
        balance = response.balance
    }
}
