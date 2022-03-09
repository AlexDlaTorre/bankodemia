package com.example.bankodemia.domain.domainObjects.User

import com.example.bankodemia.model.User

data class UserGetResponse(val userGetResponse: User.GetResponse) {
    val success: Boolean
    val data: UserGetDataDTO

    init {
        success = userGetResponse.success
        data = UserGetDataDTO(userGetResponse.data)
    }
}
