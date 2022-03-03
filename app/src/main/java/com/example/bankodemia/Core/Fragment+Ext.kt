package com.example.bankodemia.Core

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.bankodemia.R
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message: String, duration: Int) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showToastMessage(message: String, duration: Int) {
    requireContext().toast(message, duration)
}

fun Fragment.showSnackBarMessage(message: String, duration: Int) {
    view?.run { Snackbar.make(this, message, duration).show() }
}

fun Fragment.showSnackBarActionMessage(message: String, actionTitle: String, duration: Int, actionListener: View.OnClickListener) {
    val snackbar = Snackbar.make(requireView(), message, duration)
    snackbar.apply {
        setAction(actionTitle, actionListener)
        setActionTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        show()
    }
}