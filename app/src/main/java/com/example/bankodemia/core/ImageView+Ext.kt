package com.example.bankodemia.core

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import java.io.ByteArrayOutputStream

fun ImageView.toBase64(bitMap: Bitmap): String {
    val baos = ByteArrayOutputStream()
    bitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val imageBytes = baos.toByteArray()
    return Base64.encodeToString(imageBytes, Base64.DEFAULT)
}