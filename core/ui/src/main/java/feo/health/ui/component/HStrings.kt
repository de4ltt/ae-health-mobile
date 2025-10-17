package feo.health.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import feo.health.ui.R

object HStrings {
    val search
        @Composable get() = stringResource(R.string.search)

    val doctorRes
        get() = R.string.doctor
    val pharmacyRes
        get() = R.string.pharmacy
    val clinicRes
        get() = R.string.clinic

    fun String.capitalize(): String =
        this.replaceFirstChar { it.uppercase() }
}