package com.example.bankodemia.core

import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.bankodemia.R

fun activateButton(fragment: Fragment, button : Button, vararg checks: Boolean){
    val trueOnlyValues = true
    val setCheckFieldsList = mutableSetOf<Boolean>()

    for (check in checks){
        setCheckFieldsList.add(check)
    }

    //TODO : Buscar metodo que permita obtener el color desde los resources
    if (setCheckFieldsList.contains(trueOnlyValues) && setCheckFieldsList.size == 1){
        button.isEnabled = true
        button.setBackgroundColor(fragment.resources.getColor(R.color.principal_blue))
    }else{
        button.isEnabled = false
        button.setBackgroundColor(fragment.resources.getColor(R.color.principal_gray))
    }

}