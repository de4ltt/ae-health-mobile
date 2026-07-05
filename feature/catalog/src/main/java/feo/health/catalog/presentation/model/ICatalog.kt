package feo.health.catalog.presentation.model

import androidx.compose.runtime.Composable
import feo.health.catalog.presentation.component.Organization
import feo.health.catalog.presentation.component.Specialists
import feo.health.catalog.presentation.viewmodel.companion.CatalogEvent
import feo.health.ui.model.ICatalogItem

/**
 * Sealed interface representing catalog elements that can display themselves and transform to UI models.
 */
sealed interface ICatalog {

    /**
     * Transforms this catalog representation to a UI-renderable [ICatalogItem].
     *
     * @return [ICatalogItem] matching this catalog type.
     */
    fun getCatalogItem(): ICatalogItem

    /**
     * Composable function to display this catalog item's detailed UI.
     *
     * @param onEvent Callback for handling events generated within the UI component.
     */
    @Composable
    fun Display(onEvent: (CatalogEvent) -> Unit = {})

    /**
     * Data class representing a pharmacy catalog item.
     *
     * @property name Name of the pharmacy.
     * @property phoneNumber Contact phone number.
     * @property website Pharmacy website URL.
     * @property address Address of the pharmacy.
     * @property openingHours Opening schedule lines.
     * @property coords Geographical coordinates of the pharmacy.
     */
    data class Pharmacy(
        val name: String?,
        val phoneNumber: String?,
        val website: String?,
        val address: String?,
        val openingHours: List<String>,
        val coords: Coords? = null
    ) : ICatalog {
        /**
         * Transforms this pharmacy to a [ICatalogItem.PharmacyItem].
         *
         * @return An [ICatalogItem] representing the pharmacy.
         */
        override fun getCatalogItem(): ICatalogItem =
            ICatalogItem.PharmacyItem(
                title = name!!,
                imageUri = null,
                link = ""
            )

        /**
         * Composable function to display the pharmacy details UI.
         */
        @Composable
        override fun Display(onEvent: (CatalogEvent) -> Unit) =
            Organization.PharmacyItemCard(pharmacy = this, onEvent = onEvent)
    }

    /**
     * Data class representing a clinic catalog item.
     *
     * @property name Name of the clinic.
     * @property link Link for clinic details.
     * @property address Clinic address.
     * @property phoneNumber Contact phone number.
     * @property imageUri Image thumbnail URL.
     * @property itemType Type of item ("clinic" or other).
     * @property reviews List of reviews for the clinic.
     * @property coords Coords of the clinic.
     */
    data class Clinic(
        val name: String,
        val link: String,
        val address: String?,
        val phoneNumber: String?,
        val imageUri: String?,
        val itemType: String,
        val reviews: List<Review>?,
        val coords: Coords? = null
    ) : ICatalog {
        /**
         * Transforms this clinic to [ICatalogItem.ClinicItem] or [ICatalogItem.ClinicTypeItem].
         *
         * @return An [ICatalogItem] representing the clinic.
         */
        override fun getCatalogItem(): ICatalogItem =
            if (itemType == "clinic")
                ICatalogItem.ClinicItem(
                    title = name,
                    imageUri = imageUri,
                    link = link
                )
            else
                ICatalogItem.ClinicTypeItem(
                    title = name,
                    imageUri = imageUri,
                    link = link
                )

        /**
         * Composable function to display the clinic details UI.
         */
        @Composable
        override fun Display(onEvent: (CatalogEvent) -> Unit) =
            Organization.ClinicItemCard(clinic = this, onEvent = onEvent)
    }

    /**
     * Data class representing a medical service catalog item.
     *
     * @property name Name of the service.
     * @property link Link/URI path for the service details.
     * @property itemType String specifying the item type.
     */
    data class Service(
        val name: String,
        val link: String,
        val itemType: String = "service"
    ) : ICatalog {
        /**
         * Transforms this service to [ICatalogItem.ServiceItem].
         *
         * @return An [ICatalogItem] representing the service.
         */
        override fun getCatalogItem(): ICatalogItem = ICatalogItem.ServiceItem(
            title = name,
            imageUri = null,
            link = link
        )

        /**
         * Displays the service (triggers an OnDetails event).
         */
        @Composable
        override fun Display(onEvent: (CatalogEvent) -> Unit) {
            onEvent(CatalogEvent.ItemInfoEvent.OnDetails(this.getCatalogItem()))
        }
    }

    /**
     * Data class representing a doctor/specialist catalog item.
     *
     * @property name Full name of the doctor.
     * @property link Link to the doctor's details.
     * @property specialities Doctor's specialties.
     * @property experience Years of medical experience.
     * @property imageUri Photo image URI.
     * @property rating Doctor's rating.
     * @property itemType Catalog item type.
     * @property reviews Doctor reviews.
     */
    data class Doctor(
        val name: String,
        val link: String,
        val specialities: List<DoctorSpeciality>?,
        val experience: Int?,
        val imageUri: String?,
        val rating: Double?,
        val itemType: String,
        val reviews: List<Review>?,
    ) : ICatalog {

        /**
         * Helper class to break down a doctor's full name.
         *
         * @property name Doctor's first name.
         * @property surname Doctor's last name/surname.
         * @property patronymic Doctor's patronymic name, if any.
         */
        class SFullName(
            val name: String,
            val surname: String,
            val patronymic: String?
        )

        /**
         * Parses the doctor's full name string into an [SFullName] object.
         *
         * @return An [SFullName] holding parsed components.
         */
        fun getFullName(): SFullName {
            val split = name.split(" ")
            return SFullName(
                surname = split[0],
                name = split[1],
                patronymic = split.getOrNull(2)
            )
        }

        /**
         * Transforms this doctor to [ICatalogItem.DoctorItem] or [ICatalogItem.DoctorTypeItem].
         *
         * @return An [ICatalogItem] representing the doctor.
         */
        override fun getCatalogItem(): ICatalogItem =
            if (itemType == "doctor")
                ICatalogItem.DoctorItem(
                    title = name,
                    imageUri = imageUri,
                    link = link
                )
            else
                ICatalogItem.DoctorTypeItem(
                    title = name,
                    imageUri = imageUri,
                    link = link
                )

        /**
         * Composable function to display the doctor details profile screen.
         */
        @Composable
        override fun Display(onEvent: (CatalogEvent) -> Unit) =
            Specialists.Profile.Screen(specialist = this)
    }
}