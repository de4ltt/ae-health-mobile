package feo.health.ui.model

import androidx.annotation.StringRes
import feo.health.ui.R
import feo.health.ui.resource.HIcons

/**
 * Interface contract representing generic items displayed in catalog search views.
 */
sealed interface ICatalogItem {
    /**
     * Primary display header of the catalog entry.
     */
    val title: String

    /**
     * Image endpoint link or resource locator for the item icon.
     */
    val imageUri: String?

    /**
     * Deep-link redirect path string.
     */
    val link: String?

    /**
     * Category variant of the item.
     */
    val type: CatalogItemType

    /**
     * Concrete catalog item mapping representing a specialist doctor.
     *
     * @param title Doctor's full name.
     * @param imageUri Image endpoint link.
     * @param link Redirect path.
     */
    open class DoctorItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
    ) : ICatalogItem {
        override val type: CatalogItemType = CatalogItemType.DOCTOR
    }

    /**
     * Concrete catalog item mapping representing a specific doctor specialization/type.
     *
     * @param title Specialization category name.
     * @param imageUri Icon resource link.
     * @param link Redirect path.
     */
    data class DoctorTypeItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
    ) : ICatalogItem {
        override val type: CatalogItemType = CatalogItemType.DOCTOR
    }

    /**
     * Concrete catalog item mapping representing a pharmacy organization.
     *
     * @param title Pharmacy name.
     * @param imageUri Organization image link.
     * @param link Redirect path.
     */
    open class PharmacyItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
    ) : ICatalogItem {
        override val type: CatalogItemType = CatalogItemType.PHARMACY
    }

    /**
     * Concrete catalog item mapping representing a medical clinic organization.
     *
     * @param title Clinic name.
     * @param imageUri Clinic image link.
     * @param link Redirect path.
     */
    open class ClinicItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
    ) : ICatalogItem {
        override val type: CatalogItemType = CatalogItemType.CLINIC
    }

    /**
     * Concrete catalog item mapping representing a specific clinic specialization/type.
     *
     * @param title Clinic category type name.
     * @param imageUri Icon resource link.
     * @param link Redirect path.
     */
    data class ClinicTypeItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
    ) : ICatalogItem {
        override val type: CatalogItemType = CatalogItemType.DOCTOR
    }

    /**
     * Concrete catalog item mapping representing a medical service procedure.
     *
     * @param title Service name.
     * @param imageUri Icon resource link.
     * @param link Redirect path.
     */
    open class ServiceItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
    ) : ICatalogItem {
        override val type: CatalogItemType = CatalogItemType.SERVICE
    }

    /**
     * Companion metadata containing helper factory mappings and configuration enums.
     */
    companion object {

        /**
         * Generic data model used to dynamically instantiate concrete [ICatalogItem] types.
         *
         * @property title Item header text.
         * @property imageUri Image icon link.
         * @property link Redirect path.
         * @property type Catalog category type.
         */
        data class CustomCatalogItem(
            val title: String,
            val imageUri: String?,
            val link: String?,
            val type: CatalogItemType
        ) {
            /**
             * Maps this custom item to its matching concrete subclass instance of [ICatalogItem].
             *
             * @return A resolved [ICatalogItem] subclass.
             */
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

        /**
         * Enum indicating the specific category of a catalog entry.
         *
         * @property title String resource reference representing category name.
         * @property icon Default status vector icon.
         */
        enum class CatalogItemType(
            @param:StringRes val title: Int,
            val icon: HIcons
        ) {
            /**
             * Specialist doctor item category.
             */
            DOCTOR(title = R.string.doctor, icon = HIcons.DOCTOR),

            /**
             * Pharmacy item category.
             */
            PHARMACY(title = R.string.pharmacy, icon = HIcons.MEDICINE),

            /**
             * Medical clinic item category.
             */
            CLINIC(title = R.string.clinic, icon = HIcons.HOSPITAL),

            /**
             * Diagnostic/procedural service item category.
             */
            SERVICE(title = R.string.service, icon = HIcons.STETHOSCOPE),

            /**
             * Specialty type listing category for doctors.
             */
            DOCTOR_TYPE(title = R.string.doctor_type, icon = HIcons.DOCTOR),

            /**
             * Specialty type listing category for clinics.
             */
            CLINIC_TYPE(title = R.string.clinic_type, icon = HIcons.HOSPITAL);

            /**
             * Companion container providing parsing utilities.
             */
            companion object {
                /**
                 * Parses a string representation into its corresponding enum variant.
                 *
                 * @return Resolved enum type, or `null` if invalid.
                 */
                fun String.toCatalogItemType(): CatalogItemType? =
                    entries.find { it.name.equals(this, ignoreCase = true) }
            }
        }
    }
}