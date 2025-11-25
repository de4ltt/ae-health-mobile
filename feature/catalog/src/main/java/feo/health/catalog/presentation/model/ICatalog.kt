package feo.health.catalog.presentation.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.SubcomposeLayout
import feo.health.catalog.presentation.component.Organization
import feo.health.catalog.presentation.component.Specialists
import feo.health.catalog.presentation.viewmodel.companion.CatalogEvent
import feo.health.ui.model.ICatalogItem

sealed interface ICatalog {

    fun getCatalogItem(): ICatalogItem

    @Composable
    fun Display(onEvent: (CatalogEvent) -> Unit = {})

    data class Pharmacy(
        val name: String?,
        val phoneNumber: String?,
        val website: String?,
        val address: String?,
        val openingHours: List<String>,
        val coords: Coords? = null
    ) : ICatalog {
        override fun getCatalogItem(): ICatalogItem =
            ICatalogItem.PharmacyItem(
                title = name!!,
                imageUri = null,
                link = ""
            )

        @Composable
        override fun Display(onEvent: (CatalogEvent) -> Unit) =
            Organization.PharmacyItemCard(pharmacy = this, onEvent = onEvent)
    }

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

        @Composable
        override fun Display(onEvent: (CatalogEvent) -> Unit) =
            Organization.ClinicItemCard(clinic = this, onEvent = onEvent)
    }

    data class Service(
        val name: String,
        val link: String,
        val itemType: String = "service"
    ) : ICatalog {
        override fun getCatalogItem(): ICatalogItem = ICatalogItem.ServiceItem(
            title = name,
            imageUri = null,
            link = link
        )

        @Composable
        override fun Display(onEvent: (CatalogEvent) -> Unit) {
            onEvent(CatalogEvent.ItemInfoEvent.OnDetails(this.getCatalogItem()))
        }
    }

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

        class SFullName(
            val name: String,
            val surname: String,
            val patronymic: String?
        )

        fun getFullName(): SFullName {
            val split = name.split(" ")
            return SFullName(
                surname = split[0],
                name = split[1],
                patronymic = split.getOrNull(2)
            )
        }

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

        @Composable
        override fun Display(onEvent: (CatalogEvent) -> Unit) =
            Specialists.Profile.Screen(specialist = this)
    }
}