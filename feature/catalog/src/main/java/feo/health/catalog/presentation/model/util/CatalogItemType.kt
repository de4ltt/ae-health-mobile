package feo.health.catalog.presentation.model.util

import androidx.annotation.StringRes
import feo.health.ui.component.HStrings

enum class CatalogItemType(
    @param:StringRes val title: Int
) {
    DOCTOR(HStrings.doctorRes),
    PHARMACY(HStrings.pharmacyRes),
    CLINIC(HStrings.clinicRes)
}