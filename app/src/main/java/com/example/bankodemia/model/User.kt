package com.example.bankodemia.model

import com.example.bankodemia.core.IdentityType

object User {
    init { }

    data class PostResponse(
        val success: Boolean,
        val data: PostData
    )

    data class GetResponse(
        val success: Boolean,
        val data: GetData
    )

    data class Profile(
        val success: Boolean,
        val data: ProfileData,
        val balance: Int
    )

    data class ProfileData(
        val user: User,
        val transactions: List<Transaction.Transaction>
    )

    data class PostData(
        val user: User
    )

    data class GetData(
        val users: List<User>
    )

    data class User(
        val email: String,
        val name: String,
        val lastName: String,
        val occupation: String,
        val birthDate: String,
        val password: String,
        val phone: String,
        val isPhoneVerified: Boolean,
        val identityImage: String,
        val identityImageType: IdentityType
    )
}
