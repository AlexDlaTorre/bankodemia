package com.example.bankodemia.core.retrofit

import android.util.Log
import com.example.bankodemia.core.instances.SharedPreferencesInstance
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    private var mToken = SharedPreferencesInstance.getToken()
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("HeaderInterceptor", "Token inicial : $mToken")

        val request = chain.request().newBuilder()
        if (!mToken.isNullOrEmpty()) {
            Log.d("HeaderInterceptor", "Token: $mToken")
            request
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer $mToken")
        }
        return chain.proceed(request.build())
    }

}