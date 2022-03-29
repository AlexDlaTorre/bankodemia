package com.example.bankodemia.core

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.bankodemia.R
import com.example.bankodemia.core.Extensions.hideKeyboard
import com.example.bankodemia.core.types.ToastType
import com.example.bankodemia.ui.view.LoginFragment
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

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}