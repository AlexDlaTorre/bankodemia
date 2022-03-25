package com.example.bankodemia.core.utils

// Keys used to pass data
val TRANSACTIONDETAIL: String = "TransactionDetail"
val APP_ID: String = "com.example.bankodemia"

// Body keys for post request
val amountBodyKey   = "amount"
val typeBodyKey     = "type"
val conceptBodyKey  = "concept"

// json format
val jsonFormat = "application/json; charset=utf-8"

// self deposit description
val selfDeposit = "Deposito a mi cuenta"

// quotes to use in loader
val quotes: List<String> = listOf("Quienes creen que el dinero lo hace todo, terminan haciendo todo por dinero",
                                  "El amigo ha de ser como el dinero, que antes de necesitarle, se sabe el valor que tiene",
                                   "¿Qué es la riqueza? Nada, si no se gasta; nada, si se malgasta",
                                   "No pongas tu interés en el dinero, pero pon tu dinero al interés",
                                   "Abre tu puerta a la pereza y entrará a tu casa la pobreza")