package com.example.bankodemia.core.utils

import androidx.fragment.app.Fragment

interface FragmentCommunicator {
    fun showLoader(isVisible: Boolean) { }
    fun showToastMessage(message: String) { }
}