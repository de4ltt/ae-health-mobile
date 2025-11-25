package feo.health.catalog.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import feo.health.catalog.presentation.component.HFilter
import feo.health.catalog.presentation.component.Organization
import feo.health.catalog.presentation.component.Search
import feo.health.catalog.presentation.component.Search.SearchBar
import feo.health.catalog.presentation.component.Specialists
import feo.health.catalog.presentation.model.ICatalog
import feo.health.catalog.presentation.viewmodel.CatalogViewModel
import feo.health.catalog.presentation.viewmodel.companion.CatalogEvent
import feo.health.catalog.presentation.viewmodel.companion.CatalogState
import feo.health.ui.component.NavAnchors
import feo.health.ui.component.info_text.NothingFound
import feo.health.ui.component.info_text.StartTheSearch
import feo.health.ui.util.ILoading

@Composable
fun CatalogScreen(
    navHostController: NavHostController,
    catalogViewModel: CatalogViewModel
) {

    BackHandler {
        catalogViewModel.onEvent(CatalogEvent.OnBack)
    }

    LaunchedEffect(Unit) {
        NavAnchors.show()
    }

    val state by catalogViewModel.screenState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {

        AnimatedVisibility(visible = state is CatalogState.Items) {
            SearchBar { catalogViewModel.onEvent(CatalogEvent.SearchEvent.OnSearch) }
        }

        when (val screenState = state) {
            is CatalogState.ItemDetails.Found -> screenState.item.Display(onEvent = catalogViewModel::onEvent)

            is CatalogState.ItemSpecialists.Found -> Specialists.Items.Screen(
                specialists = screenState.specialists,
                onClick = catalogViewModel::onEvent
            )

            is CatalogState.Items.Found -> Search.Screen(
                screenState = catalogViewModel.screenState,
                onEvent = catalogViewModel::onEvent
            )

            is CatalogState.Items.Default -> StartTheSearch(Modifier.weight(1f))
            is CatalogState.Items.NothingFound -> NothingFound(Modifier.weight(1f))

            else -> (screenState as ILoading).LoadingScreen()
        }
    }
}
