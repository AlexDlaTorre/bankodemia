package com.example.bankodemia.domain.useCase

import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.data.model.User
import com.example.bankodemia.data.repository.UserRepository
import com.example.bankodemia.domain.domainObjects.User.createUser.UserPostResponseDTO

class CreateUserAccountUseCase {
    private val respository  = UserRepository()

    suspend operator fun invoke(user: User.SingUpCreateUser): Pair<UserPostResponseDTO?, BankodemiaError?> = respository.createUserAccount(user)
}