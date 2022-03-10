package com.example.bankodemia.domain.domainObjects.Transaction

import com.example.bankodemia.core.zero
import com.example.bankodemia.domain.domainObjects.User.UserDTO
import com.example.bankodemia.model.Transaction
import java.text.NumberFormat
import java.util.*

data class TransactionDTO(val response: Transaction.Transaction){
    val amount: Double
    val type: String
    val concept: String
    val createdAt: String
    val issuer: UserDTO
    val destinationUser: UserDTO
    val isIncome: Boolean

    val formattedAmount: String
    get() {
        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.maximumFractionDigits = Int.zero
        numberFormat.currency = Currency.getInstance("es_MX")
        return "$ ${numberFormat.format(amount)}"
    }

    init {
        amount = response.amount
        type = response.type.value
        concept = response.concept
        createdAt = response.created_at
        issuer = UserDTO(response.issuer)
        destinationUser = UserDTO(response.destinationUser)
        isIncome = response.isIncome
    }
}
