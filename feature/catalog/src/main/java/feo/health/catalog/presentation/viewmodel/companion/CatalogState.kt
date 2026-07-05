package feo.health.catalog.presentation.viewmodel.companion

import androidx.compose.runtime.Composable
import feo.health.catalog.presentation.component.Organization
import feo.health.catalog.presentation.component.Search
import feo.health.catalog.presentation.component.Specialists
import feo.health.catalog.presentation.model.ICatalog
import feo.health.ui.model.ICatalogItem
import feo.health.ui.util.ILoading

/**
 * Sealed class representing the global UI state configurations of the catalog screens.
 *
 * @property isSearchAllowed Indicates if the search input and filters are accessible in this state.
 */
sealed class CatalogState(
    open val isSearchAllowed: Boolean = true
) {

    /**
     * States related to search result items and default view.
     *
     * @property isRefreshAllowed Indicates if refreshing items is allowed.
     */
    sealed class Items(
        val isRefreshAllowed: Boolean = true
    ) : CatalogState() {
        /**
         * Default initial state before a search is executed.
         */
        data object Default : Items()

        /**
         * State indicating no search results were matched.
         */
        data object NothingFound : Items()

        /**
         * State indicating catalog search items are currently loading.
         */
        data object Loading : Items(isRefreshAllowed = false), ILoading {
            /**
             * Renders the loading screen for search results.
             */
            @Composable
            override fun LoadingScreen(vararg params: Any) =
                Search.LoadingScreen(*params)
        }

        /**
         * State containing the list of successfully retrieved search result items.
         *
         * @property found List of search results UI items.
         */
        data class Found(val found: List<ICatalogItem>) : Items()

        /**
         * State representing an error encountered during the search.
         *
         * @property message Descriptive error message.
         */
        data class Error(val message: String) : Items()
    }

    /**
     * States representing detail view screens for specific catalog items.
     *
     * @property isRefreshAllowed Indicates if refreshing details is allowed.
     */
    sealed class ItemDetails(
        val isRefreshAllowed: Boolean = true
    ) : CatalogState(isSearchAllowed = false) {
        /**
         * State showing detail loading screen skeleton.
         *
         * @property item The catalog item whose details are being requested.
         */
        data class Loading(val item: ICatalogItem) : ItemDetails(isRefreshAllowed = false),
            ILoading {
            /**
             * Renders the loading screen skeleton tailored to the selected item type.
             */
            @Composable
            override fun LoadingScreen(vararg params: Any) = when (item) {
                is ICatalogItem.ClinicItem,
                is ICatalogItem.PharmacyItem -> Organization.LoadingScreen(item.title, *params)
                is ICatalogItem.DoctorItem -> Specialists.Profile.LoadingScreen(*params)
                is ICatalogItem.ServiceItem,
                is ICatalogItem.ClinicTypeItem,
                is ICatalogItem.DoctorTypeItem -> Search.LoadingScreen(*params)
            }
        }

        /**
         * State containing the retrieved detailed content to display.
         *
         * @property item The detailed catalog item.
         */
        data class Found(val item: ICatalog) : ItemDetails()
    }

    /**
     * States related to showing specialists for an organization.
     *
     * @property isRefreshAllowed Indicates if refreshing specialists is allowed.
     * @property isSearchAllowed Indicates if search features are allowed.
     */
    sealed class ItemSpecialists(
        val isRefreshAllowed: Boolean = true,
        override val isSearchAllowed: Boolean = false
    ) : CatalogState(isSearchAllowed = isSearchAllowed) {
        /**
         * State showing specialists list loading skeleton.
         */
        data object Loading : ItemSpecialists(isRefreshAllowed = false), ILoading {
            /**
             * Renders the specialists loading screen.
             */
            @Composable
            override fun LoadingScreen(vararg params: Any) =
                Specialists.Items.LoadingScreen(*params)
        }

        /**
         * State containing successfully loaded specialists.
         *
         * @property specialists List of doctor details.
         */
        data class Found(val specialists: List<ICatalog.Doctor>) : ItemSpecialists(isSearchAllowed = true)
    }
}