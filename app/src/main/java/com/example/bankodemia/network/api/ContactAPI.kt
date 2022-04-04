package com.example.bankodemia.network.api

import com.example.bankodemia.data.model.Contact
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ContactAPI {
    @GET("/contacts")
    suspend fun getContactListInfo(): Response<Contact.GetResponse>

    @DELETE("/contacts/{id}")
    suspend fun deleteContactInfo(
        @Path("id") id: String
    ): Response<Contact.PostResponse>

    @PATCH("/contacts/{id}")
    suspend fun updateContactInfo(
        @Path("id") id: String,
        @Body contactUpdate: RequestBody
    ): Response<Contact.PostResponse>
}