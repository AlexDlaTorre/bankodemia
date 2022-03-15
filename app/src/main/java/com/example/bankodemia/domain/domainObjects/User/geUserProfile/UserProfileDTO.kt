package com.example.bankodemia.domain.domainObjects.User.geUserProfile

import com.example.bankodemia.data.model.User

class UserProfileDTO(val response: User.UserProfile) {
    val success: Boolean
    val data: UserProfileDataDTO


    init {
        success = response.success
        data = UserProfileDataDTO(response.data)
    }
}