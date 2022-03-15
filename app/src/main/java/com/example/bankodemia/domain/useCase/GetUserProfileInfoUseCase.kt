package com.example.bankodemia.domain.useCase

import com.example.bankodemia.data.repository.UserRepository
import com.example.bankodemia.domain.domainObjects.User.geUserProfile.UserProfileDTO

class GetUserProfileInfoUseCase {
    private val repository = UserRepository()

    suspend operator fun invoke(): UserProfileDTO? = repository.getUserProfileInfo()
}