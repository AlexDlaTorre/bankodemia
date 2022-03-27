package com.example.bankodemia.domain.useCase

import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.data.repository.UserRepository
import com.example.bankodemia.domain.domainObjects.User.getUsers.UserGetResponseDTO

class GetUsersByQueryUseCase {
    private val repository = UserRepository()

    suspend operator fun invoke(query: String): Pair<UserGetResponseDTO?, BankodemiaError?> = repository.getUsers(query)
}