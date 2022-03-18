package com.example.bankodemia.core


import android.app.Activity
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar

fun transitionFragment( fragmentClass: Fragment, supportFragmentManager: FragmentManager) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.add(android.R.id.content, fragmentClass)
        transaction.addToBackStack(null)
        transaction.commit()
}

fun Activity.showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.showSnackBarMessagee(message: String, duration: Int) {
        val snackBar = Snackbar.make(this.findViewById(android.R.id.content),
                                     message,
                                     duration)
        val view = snackBar.view
        val params = snackBar.view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snackBar.show()
}