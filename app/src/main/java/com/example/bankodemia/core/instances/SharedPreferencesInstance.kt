package com.example.bankodemia.core.instances

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.bankodemia.core.utils.APP_ID

object SharedPreferencesInstance {
    private val mSharedPreferencesInstance = SharedPreferencesInstance
    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var mEditor: SharedPreferences.Editor

    fun getInstance(context: Context): SharedPreferencesInstance {
        mSharedPreferences = context.getSharedPreferences(APP_ID, Activity.MODE_PRIVATE)
        mEditor = mSharedPreferences.edit()
        return mSharedPreferencesInstance
    }

    fun clean() {
        mEditor.clear()
        mEditor.apply()
    }

    fun saveToken(token: String?) {
        Log.d("SharedPreferencesInstance" ,  "El token a guardar es : $token")
        mEditor.putString("token", token)
        mEditor.apply()
    }

    fun getToken(): String? {
        return mSharedPreferences.getString("token",null)
    }


}