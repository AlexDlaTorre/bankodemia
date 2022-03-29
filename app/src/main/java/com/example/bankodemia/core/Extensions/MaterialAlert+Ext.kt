package com.example.bankodemia.core.Extensions

import android.content.DialogInterface
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun MaterialAlertDialogBuilder.negativeButton(
    text: String = "Cancelar",
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setNegativeButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}

fun MaterialAlertDialogBuilder.positiveButton(
    text: String = "Aceptar",
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setPositiveButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}