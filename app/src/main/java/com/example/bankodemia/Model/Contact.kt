package com.example.bankodemia.Model

object Contact {
    init { }
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
        val shortName: String,
        val owner: User,
        val user: User
    )
}