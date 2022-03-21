package com.example.bankodemia.core.instances

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.bankodemia.BuildConfig

object SharedPreferencesInstance {
    private val mSharedPreferencesInstance = SharedPreferencesInstance
    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var mEditor: SharedPreferences.Editor

    fun getInstance(context: Context): SharedPreferencesInstance {
        mSharedPreferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Activity.MODE_PRIVATE)
        mEditor = mSharedPreferences.edit()
        return mSharedPreferencesInstance
    }

    fun clean() {
        mEditor.clear()
        mEditor.apply()
    }
}