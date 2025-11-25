package feo.health.catalog.presentation.viewmodel.companion

import androidx.compose.runtime.Composable
import feo.health.catalog.presentation.model.ICatalog
import feo.health.ui.model.ICatalogItem
import feo.health.catalog.presentation.model.Review
import feo.health.catalog.presentation.component.Organization
import feo.health.catalog.presentation.component.Search
import feo.health.catalog.presentation.component.Specialists
import feo.health.ui.util.ILoading

sealed class CatalogState(
    open val isSearchAllowed: Boolean = true
) {

    sealed class Items(
        val isRefreshAllowed: Boolean = true
    ) : CatalogState() {
        data object Default : Items()
        data object NothingFound : Items()
        data object Loading : Items(isRefreshAllowed = false), ILoading {
            @Composable
            override fun LoadingScreen(vararg params: Any) =
                Search.LoadingScreen(*params)
        }

        data class Found(val found: List<ICatalogItem>) : Items()
    }

    sealed class ItemDetails(
        val isRefreshAllowed: Boolean = true
    ) : CatalogState(isSearchAllowed = false) {
        data class Loading(val item: ICatalogItem) : ItemDetails(isRefreshAllowed = false),
            ILoading {
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

        data class Found(val item: ICatalog) : ItemDetails()
    }

    sealed class ItemSpecialists(
        val isRefreshAllowed: Boolean = true,
        override val isSearchAllowed: Boolean = false
    ) : CatalogState(isSearchAllowed = isSearchAllowed) {
        data object Loading : ItemSpecialists(isRefreshAllowed = false), ILoading {
            @Composable
            override fun LoadingScreen(vararg params: Any) =
                Specialists.Items.LoadingScreen(*params)
        }
        data class Found(val specialists: List<ICatalog.Doctor>) : ItemSpecialists(isSearchAllowed = true)
    }
}