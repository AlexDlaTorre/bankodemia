package com.example.bankodemia.core.Extensions

val Any.TAG: String
    get() {
        return javaClass.simpleName
    }