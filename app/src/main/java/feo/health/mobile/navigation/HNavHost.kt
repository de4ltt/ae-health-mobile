package feo.health.mobile.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import feo.health.catalog.presentation.navigation.catalogNavGraph
import feo.health.catalog.presentation.viewmodel.CatalogViewModel
import feo.health.mobile.viewmodel.HViewModelProvider
import feo.health.ui.navigation.Routes

@Composable
internal fun HNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModelProvider: HViewModelProvider
) = NavHost(
    modifier = modifier,
    navController = navHostController,
    startDestination = Routes.catalog
) {
    catalogNavGraph(
        navHostController = navHostController,
        catalogViewModel = viewModelProvider<CatalogViewModel>()
    )
}