package com.example.bankodemia.core.utils

import androidx.fragment.app.Fragment

interface FragmentCommunicator {
    fun <T>sendData(data: T, destination: Fragment)
    fun goTo(destination: Fragment) { }
    fun showLoader(isVisible: Boolean) { }
    fun showToastMessage(message: String) { }
}