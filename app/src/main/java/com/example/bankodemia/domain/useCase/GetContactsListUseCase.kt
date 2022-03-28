package com.example.bankodemia.domain.useCase

import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.data.repository.ContactRepository
import com.example.bankodemia.data.repository.UserRepository
import com.example.bankodemia.domain.domainObjects.Contact.ContactGetDTO
import com.example.bankodemia.domain.domainObjects.User.geUserProfile.UserProfileDTO

class GetContactsListUseCase {
    private val repository = ContactRepository()

    suspend operator fun invoke(): Pair<ContactGetDTO?, BankodemiaError?> = repository.getContactListInfo()
}