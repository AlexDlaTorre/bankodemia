package com.example.bankodemia.data.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.example.bankodemia.core.empty
import com.example.bankodemia.core.types.IdentityType
import com.example.bankodemia.core.zero
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
        val transactions: List<Transaction.Transaction>,
        val balance: Int
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
        val lastName: String,
        val name: String,
        val email: String,
    )

    data class SignUpUser(
        @SerializedName("_id")
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
        val identityImageType: IdentityType,
        @SerializedName("__v")
        val v: Int
    )

    data class SingUpCreateUser(
        val email: String,
        val name: String,
        val lastName: String,
        val occupation: String,
        val birthDate: String,
        val password: String,
        val phone: String,
        val identityImage: String,
        val identityImageType: IdentityType
    ){
        override fun toString(): String {
            return "SingUpCreateUser(email='$email', name='$name', lastName='$lastName', occupation='$occupation', birthDate='$birthDate', password='$password', phone='$phone', identityImage='$identityImage', identityImageType=$identityImageType)"
        }
    }
}


