package com.example.bankodemia.network.api

import com.example.bankodemia.data.model.Contact
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ContactAPI {
    @GET("/contacts")
    suspend fun getContactListInfo(): Response<Contact.GetResponse>

    @DELETE("/contacts?id")
    suspend fun deleteContactInfo(
        @Query("id") id: RequestBody
    ): Response<Contact.PostResponse>

    @PATCH("/contacts?{id}")
    suspend fun updateContactInfo(
        @Part("id") id:string,
        @Body shortName: RequestBody
    ): Response<Contact.PostResponse>
}