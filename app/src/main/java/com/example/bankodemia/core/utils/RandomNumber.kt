package com.example.bankodemia.core.utils

class RandomNumber (private val firstNum: Int, private val lastNum: Int){
    fun roll(): Int {
        return (firstNum..lastNum).random()
    }
}