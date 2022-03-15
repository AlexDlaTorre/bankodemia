package com.example.bankodemia.domain.domainObjects.User.createUser

import com.example.bankodemia.data.model.User

data class UserPostDataDTO(val userPostData: User.PostData) {
    val user: SignUpUserDTO

    init {
        user = SignUpUserDTO(userPostData.user)
    }
}
