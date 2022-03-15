package com.example.bankodemia.domain.domainObjects.User.createUser

import com.example.bankodemia.data.model.User

data class UserPostResponseDTO(val userPostResponse: User.PostResponse) {
    val success: Boolean
    val data: UserPostDataDTO

    init {
        success = userPostResponse.success
        data = UserPostDataDTO(userPostResponse.data)
    }
}
