package feo.health.ui.util

import android.content.Context
import androidx.annotation.StringRes
import feo.health.ui.R
import feo.health.ui.component.HToast

object Validator {

    enum class FieldType(
        val regex: Regex,
        @param:StringRes val errorMessage: Int
    ) {
        NAME("^[A-Za-zА-Яа-я\\s'-]{2,50}$".toRegex(), R.string.invalid_name),
        PASSWORD(
            "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#\$%^&*]{6,}$".toRegex(),
            R.string.invalid_password
        ),
        EMAIL("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex(), R.string.invalid_email),
        WEIGHT(
            "^(?:[1-9]\\d{1,2}|\\d{1,2})(?:\\.\\d)?$".toRegex(),
            R.string.invalid_weight
        ),
        HEIGHT(
            "^(?:[4-9]\\d|1\\d{2}|2[0-4]\\d|250)$".toRegex(),
            R.string.invalid_height
        ),
        PHONE("^\\+?[0-9]{10,15}$".toRegex(), R.string.invalid_phone),
    }

    fun validate(context: Context, text: String, type: FieldType): Boolean {
        return if (type.regex.matches(text.trim())) {
            true
        } else {
            val stringError = context.getString(type.errorMessage)
            HToast.makeError(message = stringError, length = HToast.HToastLength.MEDIUM)
            false
        }
    }
}
