package com.example.bankodemia.core

import android.util.Patterns
import androidx.fragment.app.Fragment
import com.example.bankodemia.R
import com.google.android.material.textfield.TextInputLayout

fun validateField(
    fragment: Fragment,
    typeEnum: FieldTypeEnum = FieldTypeEnum.NO_TYPE,
    textField: TextInputLayout
): Boolean {
    var isValidField = true
    val field = textField.editText?.text.toString()

    if (field.trim().isEmpty()) {
        textField.error = fragment.getString(R.string.helper_required)
        isValidField = false
    }else if ((typeEnum == FieldTypeEnum.EMAIL) && !Patterns.EMAIL_ADDRESS.matcher(field).matches()) {
        textField.error = fragment.getString(R.string.helper_mail_no_valid)
        isValidField = false
    } else if ((typeEnum == FieldTypeEnum.PASSWORD) && !field.isPasswordValid()) {
        textField.error = fragment.getString(R.string.helper_password_no_valid)
        isValidField = false
    } else if ((typeEnum == FieldTypeEnum.TEXT) && !field.isTextValid()) {
        textField.error = fragment.getString(R.string.helper_text_no_valid)
        isValidField = false
    } else if ((typeEnum == FieldTypeEnum.ALPHANUMERIC) && !field.isAlphanumericValid()) {
        textField.error = fragment.getString(R.string.helper_text_no_valid)
        isValidField = false
    } else if ((typeEnum == FieldTypeEnum.NUMBER) && !field.isNumericValid()) {
        textField.error = fragment.getString(R.string.helper_number_no_valid)
        isValidField = false
    } else if ((typeEnum == FieldTypeEnum.PHONE) && !field.isPhoneValid()) {
        textField.error = fragment.getString(R.string.helper_phone_no_valid)
        isValidField = false
    } else if ((typeEnum == FieldTypeEnum.DATE) && !field.isDateValid()) {
        textField.error = fragment.getString(R.string.helper_date_no_valid)
        isValidField = false
    } else {
        textField.error = null
    }

    return isValidField
}

fun compareEqualsFields(fragment: Fragment,fieldToCompare : TextInputLayout, fieldComparator :TextInputLayout): Boolean{
    var isEqual = true

    when {
        fieldComparator.editText?.text?.trim().toString() != fieldToCompare.editText?.text?.trim().toString() -> {
            fieldToCompare.error =
                fragment.getString(R.string.helper_fields_no_match)
            isEqual = false
        }
        else -> {
            fieldToCompare.error = null
        }
    }

    return isEqual
}

/* TODO ("Desarrollar funcion para validar multiples campos")
fun validateFields(fragment: Fragment, vararg textFields: TextInputLayout): Boolean {
    var isValidField = true

    for (textField in textFields) {
        if (textField.editText?.text.toString().trim().isEmpty()) {
            textField.error = fragment.getString(R.string.helper_required)
            isValidField = false
        } else {
            textField.error = null
        }
    }

    return isValidField
}
*/