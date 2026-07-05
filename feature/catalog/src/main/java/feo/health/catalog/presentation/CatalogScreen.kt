package feo.health.catalog.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import feo.health.ui.component.NavAnchors
import feo.health.ui.component.info_text.NothingFound
import feo.health.catalog.presentation.component.Search
import feo.health.ui.component.info_text.StartTheSearch
import feo.health.ui.component.info_text.HErrorScreen
import feo.health.ui.navigation.CatalogDetailsRoute
import feo.health.catalog.presentation.viewmodel.CatalogViewModel
import feo.health.catalog.presentation.viewmodel.companion.CatalogEvent
import feo.health.catalog.presentation.viewmodel.companion.CatalogState
import feo.health.ui.util.ILoading

@Composable
fun CatalogScreen(
    navHostController: NavHostController,
    catalogViewModel: CatalogViewModel
) {
    LaunchedEffect(Unit) {
        NavAnchors.show()
    }

    val state by catalogViewModel.screenState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {

        AnimatedVisibility(visible = state is CatalogState.Items) {
            Search.SearchBar { catalogViewModel.onEvent(CatalogEvent.SearchEvent.OnSearch) }
        }

        when (val screenState = state) {
            is CatalogState.Items.Found -> Search.Screen(
                screenState = catalogViewModel.screenState,
                onEvent = { event ->
                    if (event is CatalogEvent.ItemInfoEvent.OnDetails) {
                        catalogViewModel.onEvent(event)
                        navHostController.navigate(CatalogDetailsRoute(event.item.link!!, event.item.type.name))
                    } else {
                        catalogViewModel.onEvent(event)
                    }
                }
            )

            is CatalogState.Items.Default -> StartTheSearch(Modifier.weight(1f))
            is CatalogState.Items.NothingFound -> NothingFound(Modifier.weight(1f))
            is CatalogState.Items.Error -> HErrorScreen(
                message = screenState.message,
                onRetry = { catalogViewModel.onEvent(CatalogEvent.SearchEvent.OnSearch) },
                modifier = Modifier.weight(1f)
            )

            else -> {
                if (screenState is ILoading) {
                    screenState.LoadingScreen()
                }
            }
        }
    }
}
