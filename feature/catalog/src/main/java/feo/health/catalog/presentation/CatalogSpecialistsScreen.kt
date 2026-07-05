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
import feo.health.catalog.presentation.component.Specialists
import feo.health.catalog.presentation.viewmodel.CatalogViewModel
import feo.health.catalog.presentation.viewmodel.companion.CatalogEvent
import feo.health.catalog.presentation.viewmodel.companion.CatalogState
import feo.health.ui.navigation.CatalogDetailsRoute

/**
 * Composable rendering the specialists list of a specific organization.
 *
 * Handles back press intercepting to update the ViewModel state before popping the back stack.
 *
 * @param navHostController Controller to pop navigation stack or move to a specialist's profile.
 * @param catalogViewModel ViewModel driving specialists states.
 * @param link The endpoint/link from which specialists are loaded.
 */
@Composable
fun CatalogSpecialistsScreen(
    navHostController: NavHostController,
    catalogViewModel: CatalogViewModel,
    link: String
) {
    BackHandler {
        catalogViewModel.onEvent(CatalogEvent.OnBack)
        navHostController.popBackStack()
    }

    val state by catalogViewModel.screenState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        when (val screenState = state) {
            is CatalogState.ItemSpecialists.Found -> {
                Specialists.Items.Screen(
                    specialists = screenState.specialists,
                    onClick = { event ->
                        if (event is CatalogEvent.ItemInfoEvent.OnDetails) {
                            catalogViewModel.onEvent(event)
                            navHostController.navigate(CatalogDetailsRoute(event.item.link!!, event.item.type.name))
                        } else {
                            catalogViewModel.onEvent(event)
                        }
                    }
                )
            }
            is CatalogState.ItemSpecialists.Loading -> {
                screenState.LoadingScreen()
            }
            else -> {
                Specialists.Items.LoadingScreen()
            }
        }
    }
}
