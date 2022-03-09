package com.example.bankodemia.domain.domainObjects.User

import com.example.bankodemia.model.User

data class UserPostResponseDTO(val userPostResponse: User.PostResponse) {
    val success: Boolean
    val data: UserPostDataDTO

    init {
        success = userPostResponse.success
        data = UserPostDataDTO(userPostResponse.data)
    }
}
