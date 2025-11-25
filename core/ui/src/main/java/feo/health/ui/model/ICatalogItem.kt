package feo.health.ui.model

import androidx.annotation.StringRes
import feo.health.ui.resource.HIcons
import feo.health.ui.resource.HStrings

sealed interface ICatalogItem {
    val title: String
    val imageUri: String?
    val link: String?
    val type: CatalogItemType

    open class DoctorItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
    ) : ICatalogItem {
        override val type: CatalogItemType = CatalogItemType.DOCTOR
    }

    data class DoctorTypeItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
    ) : ICatalogItem {
        override val type: CatalogItemType = CatalogItemType.DOCTOR
    }

    open class PharmacyItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
    ) : ICatalogItem {
        override val type: CatalogItemType = CatalogItemType.PHARMACY
    }

    open class ClinicItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
    ) : ICatalogItem {
        override val type: CatalogItemType = CatalogItemType.CLINIC
    }

    data class ClinicTypeItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
    ) : ICatalogItem {
        override val type: CatalogItemType = CatalogItemType.DOCTOR
    }

    open class ServiceItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
    ) : ICatalogItem {
        override val type: CatalogItemType = CatalogItemType.SERVICE
    }

    companion object {

        data class CustomCatalogItem(
            val title: String,
            val imageUri: String?,
            val link: String?,
            val type: CatalogItemType
        ) {
            fun toCatalogItem() = when (type) {
                CatalogItemType.DOCTOR -> DoctorItem(
                    title = title,
                    imageUri = imageUri,
                    link = link
                )

                CatalogItemType.PHARMACY -> PharmacyItem(
                    title = title,
                    imageUri = imageUri,
                    link = link
                )

                CatalogItemType.CLINIC -> ClinicItem(
                    title = title,
                    imageUri = imageUri,
                    link = link
                )

                CatalogItemType.SERVICE -> ServiceItem(
                    title = title,
                    imageUri = imageUri,
                    link = link
                )

                CatalogItemType.DOCTOR_TYPE -> DoctorTypeItem(
                    title = title,
                    imageUri = imageUri,
                    link = link
                )
                CatalogItemType.CLINIC_TYPE -> ClinicTypeItem(
                    title = title,
                    imageUri = imageUri,
                    link = link
                )
            }
        }

        enum class CatalogItemType(
            @param:StringRes val title: Int,
            val icon: HIcons
        ) {
            DOCTOR(title = HStrings.doctorRes, icon = HIcons.DOCTOR),
            PHARMACY(title = HStrings.pharmacyRes, icon = HIcons.MEDICINE),
            CLINIC(title = HStrings.clinicRes, icon = HIcons.HOSPITAL),
            SERVICE(title = HStrings.serviceRes, icon = HIcons.STETHOSCOPE),
            DOCTOR_TYPE(title = HStrings.doctorTypeRes, icon = HIcons.DOCTOR),
            CLINIC_TYPE(title = HStrings.clinicTypeRes, icon = HIcons.HOSPITAL);

            companion object {
                fun String.toCatalogItemType(): CatalogItemType? =
                    entries.find { it.name.equals(this, ignoreCase = true) }
            }
        }
    }
}