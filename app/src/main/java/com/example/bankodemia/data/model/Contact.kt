package com.example.bankodemia.data.model

class Contact {
    data class PostResponse(
        val success: Boolean,
        val data: PostData
        )

    data class GetResponse(
        val success: Boolean,
        val data: GetData
    )

    data class PostData(
        val contact: Contact
    )

    data class GetData(
        val contacts: List<Contact>
    )

    data class Contact(
        val _id: String,
        val shortName: String,
        val owner: User.User,
        val user: User.User
    )
    data class IdContact(
        val id: String
    )
}