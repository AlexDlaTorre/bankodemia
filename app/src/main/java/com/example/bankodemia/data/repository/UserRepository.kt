package com.example.bankodemia.data.repository

import com.example.bankodemia.data.model.User
import com.example.bankodemia.domain.domainObjects.User.geUserProfile.UserProfileDTO
import com.example.bankodemia.domain.domainObjects.User.getUsers.UserGetResponseDTO
import com.example.bankodemia.network.service.UserServiceNetwork

class UserRepository {
    private val api = UserServiceNetwork()

    suspend fun getUserProfileInfo(): UserProfileDTO? {
        val response = api.getUserProfileInfo()
        val profileDTO = response?.let { UserProfileDTO(it) }
        return profileDTO
    }

    suspend fun getUsers(): UserGetResponseDTO? {
        val response = api.getUsers()
        val users = response?.let { UserGetResponseDTO(it) }
        return users
    }
}