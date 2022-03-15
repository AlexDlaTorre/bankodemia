package com.example.bankodemia.core.utils

data class RouterEmitter<T>(
    val target: Int,
    val params: T? = null
)
