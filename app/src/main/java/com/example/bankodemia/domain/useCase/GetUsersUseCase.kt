package com.example.bankodemia.domain.useCase

import com.example.bankodemia.data.repository.UserRepository
import com.example.bankodemia.domain.domainObjects.User.getUsers.UserGetResponseDTO

class GetUsersUseCase {
    private val repository = UserRepository()

    suspend operator fun invoke(): UserGetResponseDTO? = repository.getUsers()
}