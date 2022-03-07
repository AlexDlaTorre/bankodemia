package com.example.bankodemia.core

enum class IdentityType(val value: String) {
    INE("INE")
}

enum class MovementType(val value: String) {
    DEPOSIT("DEPOSIT")
}

enum class ErrorCodes(val value: Int) {
    INSUFICIENTFOUNDS(412),
    BROKE(402),
    BADREQUEST(400)
}