package com.example.bankodemia.data.model

import com.example.bankodemia.core.types.IdentityType
import com.google.gson.annotations.SerializedName

class User {
    data class PostResponse(
        val success: Boolean,
        val data: PostData
    )

    data class GetResponse(
        val success: Boolean,
        val data: GetData
    )

    data class UserProfile(
        val success: Boolean,
        val data: ProfileData,
    )

    data class ProfileData(
        val user: User,
        val transactions: List<TransactionProfile>,
        val balance: Int
    )

    data class TransactionProfile(
        @SerializedName("_id")
        val id: String,
        val isIncome: Boolean,
        val amount: Int,
        val type: String,
        val concept: String,
        @SerializedName("created_at")
        val createdAt: String,
        val issuer: SignUpUser,
        val destinationUser: SignUpUser
    )

    data class PostData(
        val user: SignUpUser
    )

    data class GetData(
        val users: List<User>
    )

    data class User(
        @SerializedName("_id")
        val id: String,
        val phone: String,
        val lastName: String,
        val name: String,
        val email: String,
    )

    data class SignUpUser(
        val id: String,
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


