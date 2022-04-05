package com.example.bankodemia.domain.useCase

import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.data.repository.ContactRepository
import com.example.bankodemia.domain.domainObjects.Contact.ContactPostDTO
import okhttp3.RequestBody

class CreateContactUseCase {
    private val repository = ContactRepository()

    suspend operator fun invoke(createContact: RequestBody
    ): Pair<ContactPostDTO?, BankodemiaError?> = repository.createContactInfo(createContact)
}