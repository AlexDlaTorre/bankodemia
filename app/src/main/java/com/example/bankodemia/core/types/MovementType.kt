package com.example.bankodemia.core.types

enum class MovementType(val type: String) {
    PAYMEMT("PAYMENT"),
    DEPOSIT("DEPOSIT");

    companion object {
        private val map = values().associateBy(MovementType::type)
        fun fromType(type: String) = map[type]
    }
}

fun getMovementType(type: String): MovementType? = MovementType.fromType(type)