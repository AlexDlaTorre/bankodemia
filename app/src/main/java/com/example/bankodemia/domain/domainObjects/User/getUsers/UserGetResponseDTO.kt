package com.example.bankodemia.domain.domainObjects.User.getUsers

import com.example.bankodemia.data.model.User

data class UserGetResponseDTO(val userGetResponse: User.GetResponse) {
    val success: Boolean
    val data: UserGetDataDTO

    init {
        success = userGetResponse.success
        data = UserGetDataDTO(userGetResponse.data)
    }
}
