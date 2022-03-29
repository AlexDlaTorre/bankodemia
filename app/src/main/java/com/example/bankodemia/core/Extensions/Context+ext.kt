package com.example.bankodemia.core.Extensions

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StyleRes
import com.example.bankodemia.core.zero
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, Int.zero)
}

// Kotlin extension
fun Context.alert(
    @StyleRes style: Int = 0,
    dialogBuilder: MaterialAlertDialogBuilder.() -> Unit
) {
    MaterialAlertDialogBuilder(this, style)
        .apply {
            setCancelable(false)
            dialogBuilder()
            create()
            show()
        }
}