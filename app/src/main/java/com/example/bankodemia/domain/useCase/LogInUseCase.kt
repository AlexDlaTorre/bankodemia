package com.example.bankodemia.domain.useCase

import com.example.bankodemia.data.model.Auth
import com.example.bankodemia.data.repository.LogInRepository
import com.example.bankodemia.domain.domainObjects.Auth.AuthDTO

class LogInUseCase {
    private val repository = LogInRepository()

    suspend operator fun invoke(login: Auth.AuthLogIn): AuthDTO? = repository.logIn(login)
}