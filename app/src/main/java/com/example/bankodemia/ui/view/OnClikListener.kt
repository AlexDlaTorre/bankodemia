package com.example.bankodemia.UI.view

import com.example.bankodemia.data.model.Lada

interface OnClikListener {
    fun onClick(lada: Lada, position: Int)
}