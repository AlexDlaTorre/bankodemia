package com.example.bankodemia.domain.domainObjects.User

import com.example.bankodemia.model.User

class UserProfileDTO(val response: User.Profile) {
    val success: Boolean
    val data: UserProfileDataDTO
    val balance: Int

    init {
        success = response.success
        data = UserProfileDataDTO(response.data)
        balance = response.balance
    }
}