package feo.health.user.components.presentation.model

import feo.health.ui.model.ICatalogItem
import feo.health.ui.model.ICatalogItem.ClinicItem
import feo.health.ui.model.ICatalogItem.DoctorItem
import feo.health.ui.model.ICatalogItem.PharmacyItem
import feo.health.ui.model.ICatalogItem.ServiceItem
import java.time.LocalDateTime

/**
 * Sealed interface representing presentation-level catalog items.
 * Contains common properties for items that can be displayed in lists.
 */
sealed interface UCatalogItem {

    /**
     * The title of the catalog item.
     */
    val title: String

    /**
     * Optional URI representing an image for the catalog item.
     */
    val imageUri: String?

    /**
     * Optional web link or deep link to more information about the item.
     */
    val link: String?

    /**
     * The date and time associated with the item (e.g. view time or scheduled time).
     */
    val dateTime: LocalDateTime

    /**
     * The type classification of the catalog item.
     */
    val type: ICatalogItem.Companion.CatalogItemType

    /**
     * Represents a doctor catalog item in presentation module.
     *
     * @property title The title of the doctor item.
     * @property imageUri Optional URI of the doctor's image.
     * @property link Optional link for additional doctor info.
     * @property dateTime The date and time associated with this doctor item.
     */
    data class UDoctorItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
        override val dateTime: LocalDateTime
    ) : DoctorItem(title, imageUri, link), UCatalogItem {
         /**
          * The type classification of this doctor catalog item.
          */
         override val type: ICatalogItem.Companion.CatalogItemType = super.type
    }

    /**
     * Represents a pharmacy catalog item in presentation module.
     *
     * @property title The title of the pharmacy item.
     * @property imageUri Optional URI of the pharmacy's image.
     * @property link Optional link for additional pharmacy info.
     * @property dateTime The date and time associated with this pharmacy item.
     */
    data class UPharmacyItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
        override val dateTime: LocalDateTime
    ) : PharmacyItem(title, imageUri, link), UCatalogItem {
        /**
         * The type classification of this pharmacy catalog item.
         */
        override val type: ICatalogItem.Companion.CatalogItemType = super.type
    }

    /**
     * Represents a clinic catalog item in presentation module.
     *
     * @property title The title of the clinic item.
     * @property imageUri Optional URI of the clinic's image.
     * @property link Optional link for additional clinic info.
     * @property dateTime The date and time associated with this clinic item.
     */
    data class UClinicItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
        override val dateTime: LocalDateTime
    ) : ClinicItem(title, imageUri, link), UCatalogItem {
        /**
         * The type classification of this clinic catalog item.
         */
        override val type: ICatalogItem.Companion.CatalogItemType = super.type
    }

    /**
     * Represents a service catalog item in presentation module.
     *
     * @property title The title of the service item.
     * @property imageUri Optional URI of the service's image.
     * @property link Optional link for additional service info.
     * @property dateTime The date and time associated with this service item.
     */
    data class UServiceItem(
        override val title: String,
        override val imageUri: String?,
        override val link: String?,
        override val dateTime: LocalDateTime
    ) : ServiceItem(title, imageUri, link), UCatalogItem {
        /**
         * The type classification of this service catalog item.
         */
        override val type: ICatalogItem.Companion.CatalogItemType = super.type
    }

}