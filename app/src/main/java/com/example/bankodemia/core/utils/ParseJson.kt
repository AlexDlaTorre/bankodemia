package com.example.bankodemia.core.utils

import android.content.Context
import android.util.JsonReader
import com.example.bankodemia.data.model.Lada
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class ParseJson {
    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}