package com.example.bankodemia.domain.domainObjects.Transaction

import android.icu.number.NumberFormatter
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.bankodemia.core.types.MovementType
import com.example.bankodemia.core.types.getMovementType
import com.example.bankodemia.core.zero
import com.example.bankodemia.data.model.Transaction
import com.example.bankodemia.data.model.User
import com.example.bankodemia.domain.domainObjects.User.UserDTO
import java.text.NumberFormat
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class TransactionDTO(val response: Transaction.Transaction): java.io.Serializable {
    val id: String
    val amount: Int
    val type: MovementType
    val concept: String
    val createdAt: String
    val issuer: UserDTO
    val destinationUser: UserDTO?
    val isIncome: Boolean?

    val formattedAmount: String
    get() {
        val format = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = Int.zero
        if (type == MovementType.DEPOSIT) {
            return "+ ${format.format(amount.toDouble())}"
        } else {
            return "- ${format.format(amount.toDouble())}"
        }
    }

    val formattedTime: String
    @RequiresApi(Build.VERSION_CODES.O)
    get() {
        val date = ZonedDateTime.parse(createdAt)
        val formatter = DateTimeFormatter.ofPattern("HH:MM a")
        return date.format(formatter)
    }

    val formattedDate: String
    @RequiresApi(Build.VERSION_CODES.O)
    get() {
        val date = ZonedDateTime.parse(createdAt)
        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy HH:MM a", Locale("es", "ES"))
        return date.format(formatter)
    }

    val date: LocalDate?
    @RequiresApi(Build.VERSION_CODES.O)
    get() {
        return LocalDate.parse(createdAt, DateTimeFormatter.ISO_DATE_TIME)
    }

    init {
        id = response.id
        amount = response.amount
        type = response.type
        concept = response.concept
        createdAt = response.createdAt
        issuer = UserDTO(response.issuer)
        destinationUser = UserDTO(response.destinationUser)
        isIncome = response.isIncome
    }
}
