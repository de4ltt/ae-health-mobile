package feo.health.ui.util

import android.content.Context
import androidx.annotation.StringRes
import feo.health.ui.R
import feo.health.ui.component.HToast

/**
 * Validation utility that validates string inputs against standard formats (Emails, Passwords, etc.)
 * and enqueues user-facing error notifications upon failures.
 */
object Validator {

    /**
     * Enum mapping containing regular expression matches and corresponding string error resource IDs.
     *
     * @property regex Regex validation pattern.
     * @property errorMessage Res ID detailing validation error.
     */
    enum class FieldType(
        val regex: Regex,
        @param:StringRes val errorMessage: Int
    ) {
        /**
         * Names validator allowing alphabetic symbols and spaces.
         */
        NAME("^[A-Za-zА-Яа-я\\s'-]{2,50}$".toRegex(), R.string.invalid_name),

        /**
         * Passwords validator requiring minimum 6 characters with letters and numbers.
         */
        PASSWORD(
            "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#\$%^&*]{6,}$".toRegex(),
            R.string.invalid_password
        ),

        /**
         * Standard email validator format.
         */
        EMAIL("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex(), R.string.invalid_email),

        /**
         * Weight metric validator formats.
         */
        WEIGHT(
            "^(?:[1-9]\\d{1,2}|\\d{1,2})(?:\\.\\d)?$".toRegex(),
            R.string.invalid_weight
        ),

        /**
         * Height metric validator formats.
         */
        HEIGHT(
            "^(?:[4-9]\\d|1\\d{2}|2[0-4]\\d|250)$".toRegex(),
            R.string.invalid_height
        ),

        /**
         * Telephone dialing numbers validator formats.
         */
        PHONE("^\\+?[0-9]{10,15}$".toRegex(), R.string.invalid_phone),
    }

    /**
     * Checks input text against validation category rules. Enqueues a toast warning on failure.
     *
     * @param context Active system context context.
     * @param text The input text to validate.
     * @param type Field validation category.
     * @return `true` if input is valid, `false` otherwise.
     */
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
