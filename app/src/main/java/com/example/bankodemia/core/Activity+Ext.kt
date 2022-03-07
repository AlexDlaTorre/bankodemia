package com.example.bankodemia.core


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun transitionFragment( fragmentClass: Fragment, supportFragmentManager: FragmentManager) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.add(android.R.id.content, fragmentClass)
        transaction.addToBackStack(null)
        transaction.commit()
}