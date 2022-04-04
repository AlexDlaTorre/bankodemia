package com.example.bankodemia.domain.useCase

import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.data.repository.ContactRepository
import com.example.bankodemia.domain.domainObjects.Contact.ContactPostDTO

class DeleteContactIdUseCase {
    private val repository = ContactRepository()

    suspend operator fun invoke(id: String): Pair<ContactPostDTO?, BankodemiaError?> =
        repository.deleteContactInfo(id)
}