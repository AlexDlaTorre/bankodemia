package com.example.bankodemia.domain.domainObjects.User

import com.example.bankodemia.model.User

data class UserGetDataDTO(val userData: User.GetData) {
    val users: List<UserDTO>

    init {
        users = userData.users.map { UserDTO(it) }
    }
}
