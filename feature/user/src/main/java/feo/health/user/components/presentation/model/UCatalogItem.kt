package feo.health.user.components.presentation.model

import feo.health.ui.model.ICatalogItem
import feo.health.ui.model.ICatalogItem.PharmacyItem
import feo.health.ui.model.ICatalogItem.ServiceItem
import feo.health.ui.model.ICatalogItem.ClinicItem
import feo.health.ui.model.ICatalogItem.DoctorItem
import java.time.LocalDateTime

sealed interface UCatalogItem {

    val title: String
    val imageUri: String?
    val link: String?
    val dateTime: LocalDateTime
    val type: ICatalogItem.Companion.CatalogItemType

    data class UDoctorItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
        override val dateTime: LocalDateTime
    ) : DoctorItem(title, imageUri, link), UCatalogItem {
         override val type: ICatalogItem.Companion.CatalogItemType = super.type
    }

    data class UPharmacyItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
        override val dateTime: LocalDateTime
    ) : PharmacyItem(title, imageUri, link), UCatalogItem {
        override val type: ICatalogItem.Companion.CatalogItemType = super.type
    }

    data class UClinicItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
        override val dateTime: LocalDateTime
    ) : ClinicItem(title, imageUri, link), UCatalogItem {
        override val type: ICatalogItem.Companion.CatalogItemType = super.type
    }

    data class UServiceItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
        override val dateTime: LocalDateTime
    ) : ServiceItem(title, imageUri, link), UCatalogItem {
        override val type: ICatalogItem.Companion.CatalogItemType = super.type
    }

}