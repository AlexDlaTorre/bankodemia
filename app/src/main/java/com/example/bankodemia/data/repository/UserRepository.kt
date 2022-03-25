package com.example.bankodemia.data.repository

import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.domain.domainObjects.User.geUserProfile.UserProfileDTO
import com.example.bankodemia.network.service.UserServiceNetwork

class UserRepository {
    private val api = UserServiceNetwork()

    suspend fun getUserProfileInfo(): Pair<UserProfileDTO?, BankodemiaError?> {
        val response = api.getUserProfileInfo()
        val profileDTO = response?.let { it.first?.let { user -> UserProfileDTO(user) } }
        return profileDTO to response.second
    }
}