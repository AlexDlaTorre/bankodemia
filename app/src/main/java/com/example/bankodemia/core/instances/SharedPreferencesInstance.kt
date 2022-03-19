package com.example.bankodemia.core.instances

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesInstance {
    private val mSharedPreferencesInstance = SharedPreferencesInstance
    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var mEditor: SharedPreferences.Editor

    fun getInstance(context: Context): SharedPreferencesInstance {
        if (mSharedPreferences == null) {
            mSharedPreferences =
                context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
            mEditor = mSharedPreferences.edit()
        }
        return mSharedPreferencesInstance
    }

    fun clean() {
        mEditor.clear()
        mEditor.apply()
    }
}