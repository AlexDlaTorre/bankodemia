package com.example.bankodemia.network.api

import com.example.bankodemia.data.model.Contact
import com.example.bankodemia.data.model.User
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ContactAPI {
    @GET("/contacts")
    suspend fun getContactListInfo(): Response<Contact.GetResponse>

    @DELETE("/contacts/id")
    suspend fun deleteContactInfo(
        @Part("id") id: String
    ): Response<Contact.PostResponse>

    @PATCH("/contacts/id")
    suspend fun updateContactInfo(
        @Part("id") id:String,
        @Body shortName: RequestBody
    ): Response<Contact.PostResponse>
}