package com.example.bankodemia.network.api

import com.example.bankodemia.data.model.Contact
import retrofit2.Response
import retrofit2.http.GET

interface ContactAPI {
    @GET("/contacts")
    suspend fun getContactListInfo(): Response<Contact.GetResponse>
}