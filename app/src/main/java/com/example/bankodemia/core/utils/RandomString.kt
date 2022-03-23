package com.example.bankodemia.core.utils

import kotlin.random.Random

class RandomString (private val bankList:Array<String>){
    fun rollBank(): String{
        val randomValue = Random.nextInt(bankList.size)
        return bankList[randomValue]
    }
}