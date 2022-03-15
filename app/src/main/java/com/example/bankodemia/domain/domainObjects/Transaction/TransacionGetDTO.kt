package com.example.bankodemia.domain.domainObjects.Transaction

import com.example.bankodemia.data.model.Transaction

data class TransacionGetDTO(val response: Transaction.GetResponse) {
    val success: Boolean
    val data: TransactionGetDataDTO

    init {
        success = response.success
        data = TransactionGetDataDTO(response.data)
    }
}
