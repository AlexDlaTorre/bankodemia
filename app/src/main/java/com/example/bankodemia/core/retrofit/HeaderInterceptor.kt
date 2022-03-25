package com.example.bankodemia.core.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    private var mToken = ""

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (!mToken.isNullOrEmpty()) {
            request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer $mToken")
                .build()
        }
        return chain.proceed(request)
    }

    fun setToken(token: String) {
        this.mToken = token
    }
}