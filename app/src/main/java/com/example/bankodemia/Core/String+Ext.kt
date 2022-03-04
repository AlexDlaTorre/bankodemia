package com.example.bankodemia.Core

import java.util.regex.Pattern

fun String.isEmailValid(): Boolean {
    val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
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