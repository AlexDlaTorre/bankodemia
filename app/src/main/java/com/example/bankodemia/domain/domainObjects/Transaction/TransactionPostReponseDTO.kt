package com.example.bankodemia.domain.domainObjects.Transaction

import com.example.bankodemia.core.zero
import com.example.bankodemia.data.model.Transaction

data class TransactionPostReponseDTO(val response: Transaction.PostResponse) {
    val success: Boolean
    val data: TransactionPostDataDTO

    init {
        success = response.success
        data = TransactionPostDataDTO(response.data)
    }
}
