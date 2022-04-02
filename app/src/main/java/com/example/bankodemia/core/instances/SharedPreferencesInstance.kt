package com.example.bankodemia.core.instances

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.bankodemia.R
import com.example.bankodemia.core.Extensions.TAG
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
        Log.d(TAG,  "El token a guardar es : $token")
        mEditor.putString("token", token)
        mEditor.apply()
    }

    fun getToken(): String? {
        return mSharedPreferences.getString("token",null)
    }

    fun clearToken(){
        mEditor.putString("token",null)
        mEditor.apply()
    }

    fun getStringValue(key: String): String? {
        return mSharedPreferences.getString(key, null)
    }

    fun getBooleanValue(key: String): Boolean {
        return mSharedPreferences.getBoolean(key, false)
    }

    fun getLongValue(key: String): Long {
        return mSharedPreferences.getLong(key, -1)
    }

    fun setStringValue(key: String, value: String?) {
        mEditor.putString(key, value)
        mEditor.apply()
    }

    fun setBooleanValue(key: String, value: Boolean) {
        mEditor.putBoolean(key, value)
        mEditor.apply()
    }

    fun setLongValue(key: String, value: Long) {
        mEditor.putLong(key, value)
        mEditor.apply()
    }

    fun cleanFieldsCreateAccount(context: Context){
        with(mEditor){
            putString(context.getString(R.string.saved_mail),null)
            putString(context.getString(R.string.saved_name),null)
            putString(context.getString(R.string.saved_last_name),null)
            putString(context.getString(R.string.saved_ocuppation),null)
            putString(context.getString(R.string.saved_birthday),null)
            putString(context.getString(R.string.saved_telephone),null)
            putString(context.getString(R.string.saved_telephone_format),null)
            putString(context.getString(R.string.saved_image_string),null)
            putString(context.getString(R.string.saved_document),null)
            apply()
        }
    }

}