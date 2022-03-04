package com.example.bankodemia.Core

import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


fun transitionFragment(
    button: Button,
    fragmentName: Fragment,
    supportFragmentManager: FragmentManager
) {
    button.setOnClickListener {
        val fragmentManager: FragmentManager = (supportFragmentManager)
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.add(android.R.id.content, fragmentName)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}