package feo.health.catalog.presentation.viewmodel.companion

import feo.health.ui.model.ICatalogItem
import feo.health.catalog.presentation.model.Review

sealed interface CatalogEvent {

    sealed interface SearchEvent : CatalogEvent {
        data object OnSearch : SearchEvent
    }

    sealed interface ItemInfoEvent : CatalogEvent {
        data class OnDetails(val item: ICatalogItem) : ItemInfoEvent
        data class OnSpecialists(
            val type: ICatalogItem.Companion.CatalogItemType,
            val link: String
        ) : ItemInfoEvent
    }

    data object OnBack : CatalogEvent

}