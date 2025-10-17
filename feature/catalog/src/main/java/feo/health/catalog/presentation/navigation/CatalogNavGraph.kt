package feo.health.catalog.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import feo.health.catalog.presentation.CatalogScreen
import feo.health.catalog.presentation.viewmodel.CatalogViewModel
import feo.health.ui.navigation.Routes

fun NavGraphBuilder.catalogNavGraph(
    navHostController: NavHostController,
    catalogViewModel: CatalogViewModel
) {
    composable(route = Routes.catalog) {
        CatalogScreen(
            navHostController = navHostController,
            catalogViewModel = catalogViewModel
        )
    }
}