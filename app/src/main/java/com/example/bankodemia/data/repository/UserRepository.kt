package com.example.bankodemia.data.repository

import com.example.bankodemia.domain.domainObjects.User.geUserProfile.UserProfileDTO
import com.example.bankodemia.network.service.UserServiceNetwork

class UserRepository {
    private val api = UserServiceNetwork()

    suspend fun getUserProfileInfo(): UserProfileDTO? {
        val response = api.getUserProfileInfo()
        val profileDTO = response?.let { UserProfileDTO(it) }
        return profileDTO
    }
}