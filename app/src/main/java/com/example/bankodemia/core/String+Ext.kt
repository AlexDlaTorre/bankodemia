package com.example.bankodemia.core

import java.util.regex.Pattern
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

fun String.isEmailValid(): Boolean {
    val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.base64ToImage(): Bitmap {
    val imageBytes: ByteArray = Base64.decode(this, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}

fun String.isPasswordValid(): Boolean{
    /**
     * TODO (Buscar regex o funcion que pueda distinguir caracteres continuos y repetidos)
     * Expression Minimum six characters, at least one letter and one number:
     * */
    val expression = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isTextValid(): Boolean{
    val expression = "^[a-zA-Z ]*$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isNumericValid(): Boolean{
    val expression = "^[0-9 ]*$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isAlphanumericValid(): Boolean{
    val expression = "^[a-zA-Z0-9 ]*$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isPhoneValid(): Boolean{
    val expression = "^\\+?[0-9]*\$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isDateValid(): Boolean{
    val expression = "^[a-zA-Z0-9 \\-_/]*$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

var String.empty: String
    get() = ""
    set(value) { "" }


var String.dots: String
    get() = ":"
    set(value) { ":" }

var String.hypen: String
    get() = "-"
    set(value) { "-" }

var String.newLine: String
    get() = "\n"
    set(value) { "\n" }

var String.slash: String
    get() = "/"
    set(value) { "/" }

var String.whiteSpace: String
    get() = " "
    set(value) { " " }

var String.percentage: String
    get() = "%"
    set(value) { "%" }