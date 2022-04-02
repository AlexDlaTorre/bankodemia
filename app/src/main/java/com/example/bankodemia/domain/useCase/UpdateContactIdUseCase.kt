package com.example.bankodemia.domain.useCase

import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.data.repository.ContactRepository
import com.example.bankodemia.domain.domainObjects.Contact.ContactGetDTO
import com.example.bankodemia.domain.domainObjects.Contact.ContactPostDTO
import okhttp3.RequestBody

class UpdateContactIdUseCase {
    private val repository = ContactRepository()

    suspend operator fun invoke(parametersUdate: RequestBody): Pair<ContactPostDTO?, BankodemiaError?> = repository.upDateContactInfo(parametersUdate)
}