package com.example.bankodemia.data.model

import com.google.gson.annotations.SerializedName

data class Service(
    @SerializedName("name_service")
    val nameService: String?,
    @SerializedName("providers_example")
    val providersExample: String?
)
