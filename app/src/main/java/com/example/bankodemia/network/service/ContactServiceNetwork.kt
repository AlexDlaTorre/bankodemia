package com.example.bankodemia.network.service

import android.util.Log
import com.example.bankodemia.core.instances.RetrofitBankodemiaInstance
import com.example.bankodemia.core.utils.createApiError
import com.example.bankodemia.data.model.BankodemiaError
import com.example.bankodemia.data.model.BankodemiaErrorResponse
import com.example.bankodemia.data.model.Contact
import com.example.bankodemia.data.model.User
import com.example.bankodemia.network.api.ContactAPI
import com.example.bankodemia.network.api.UserAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactServiceNetwork {
    private val retrofit = RetrofitBankodemiaInstance.getRetrofit().create(ContactAPI::class.java)

    suspend fun getContactList(): Pair<Contact.GetResponse?, BankodemiaError?> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.getContactListInfo()
            Log.d("ContactsListResponse", response.body().toString())

            val responseBody = response.body()
            if (responseBody != null) {
                responseBody to null
            } else {
                val errorResponse = createApiError(response, BankodemiaErrorResponse::class.java)
                null to errorResponse
            }
        }
    }
}