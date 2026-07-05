package feo.health.catalog.presentation.viewmodel.companion

import feo.health.ui.model.ICatalogItem

/**
 * Sealed interface representing all events that can be dispatched to the [CatalogViewModel].
 */
sealed interface CatalogEvent {

    /**
     * Events related to performing search operations in the catalog.
     */
    sealed interface SearchEvent : CatalogEvent {
        /**
         * Dispatched to trigger a new search query.
         */
        data object OnSearch : SearchEvent
    }

    /**
     * Events related to retrieving and showing detailed information for catalog items.
     */
    sealed interface ItemInfoEvent : CatalogEvent {
        /**
         * Dispatched when the user requests detail info for a specific catalog item.
         *
         * @property item The requested catalog item.
         */
        data class OnDetails(val item: ICatalogItem) : ItemInfoEvent

        /**
         * Dispatched when the user requests the list of specialists for an organization.
         *
         * @property type The category type of the organization.
         * @property link The link key of the organization.
         */
        data class OnSpecialists(
            val type: ICatalogItem.Companion.CatalogItemType,
            val link: String
        ) : ItemInfoEvent
    }

    /**
     * Dispatched to navigate back to the previous catalog state/screen.
     */
    data object OnBack : CatalogEvent

}