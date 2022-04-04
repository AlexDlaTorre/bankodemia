package com.example.bankodemia.core.types

enum class IdentityType(val value: String) {
    INE("INE"),
    PASSPORT("PASSPORT"),
    MIGRATION_FORM("MIGRATION_FORM")
}

enum class ErrorCodes(val value: Int) {
    INSUFICIENTFOUNDS(412),
    BROKE(402),
    BADREQUEST(400)
}