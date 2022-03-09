package com.example.bankodemia.domain.domainObjects.User

import com.example.bankodemia.model.User

data class UserPostDataDTO(val userPostData: User.PostData) {
    val user: UserDTO

    init {
        user = UserDTO(userPostData.user)
    }
}
