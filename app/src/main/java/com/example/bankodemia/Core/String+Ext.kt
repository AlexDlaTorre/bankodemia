package com.example.bankodemia.Core

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.util.regex.Pattern

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