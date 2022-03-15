package com.example.bankodemia.core.types

enum class IdentityType(val value: String) {
    INE("INE")
}

enum class ErrorCodes(val value: Int) {
    INSUFICIENTFOUNDS(412),
    BROKE(402),
    BADREQUEST(400)
}