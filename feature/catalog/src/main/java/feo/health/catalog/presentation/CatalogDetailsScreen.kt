package feo.health.catalog.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import feo.health.catalog.presentation.viewmodel.CatalogViewModel
import feo.health.catalog.presentation.viewmodel.companion.CatalogEvent
import feo.health.catalog.presentation.viewmodel.companion.CatalogState
import feo.health.ui.navigation.CatalogDetailsRoute
import feo.health.ui.navigation.CatalogSpecialistsRoute

/**
 * Composable rendering the detailed view of a selected catalog item.
 *
 * Handles back press intercepting to update the ViewModel state before popping the back stack.
 *
 * @param navHostController Controller for navigating back or nested details.
 * @param catalogViewModel ViewModel driving details states.
 * @param link The details URL/link of the catalog item.
 * @param type The type name of the catalog item (e.g. Clinic, Doctor, Pharmacy).
 */
@Composable
fun CatalogDetailsScreen(
    navHostController: NavHostController,
    catalogViewModel: CatalogViewModel,
    link: String,
    type: String
) {
    BackHandler {
        catalogViewModel.onEvent(CatalogEvent.OnBack)
        navHostController.popBackStack()
    }

    val state by catalogViewModel.screenState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        when (val screenState = state) {
            is CatalogState.ItemDetails.Found -> {
                screenState.item.Display(onEvent = { event ->
                    if (event is CatalogEvent.ItemInfoEvent.OnSpecialists) {
                        catalogViewModel.onEvent(event)
                        navHostController.navigate(CatalogSpecialistsRoute(event.link))
                    } else if (event is CatalogEvent.ItemInfoEvent.OnDetails) {
                        catalogViewModel.onEvent(event)
                        navHostController.navigate(CatalogDetailsRoute(event.item.link!!, event.item.type.name))
                    } else {
                        catalogViewModel.onEvent(event)
                    }
                })
            }
            is CatalogState.ItemDetails.Loading -> {
                screenState.LoadingScreen()
            }
            else -> {
                feo.health.catalog.presentation.component.Organization.LoadingScreen()
            }
        }
    }
}
