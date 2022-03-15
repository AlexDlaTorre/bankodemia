package com.example.bankodemia.domain.domainObjects.User.getUsers

import com.example.bankodemia.data.model.User
import com.example.bankodemia.domain.domainObjects.User.UserDTO

data class UserGetDataDTO(val userData: User.GetData) {
    val users: List<UserDTO>

    init {
        users = userData.users.map { UserDTO(it) }
    }
}
