package feo.health.catalog.presentation.navigation

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import feo.health.catalog.presentation.CatalogScreen
import feo.health.catalog.presentation.viewmodel.CatalogViewModel
import feo.health.catalog.presentation.viewmodel.companion.CatalogEvent
import feo.health.catalog.presentation.viewmodel.companion.SearchBarState
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
    composable(
        route = Routes.catalog + "/{name}",
        arguments = listOf(navArgument("name") { type = NavType.StringType })
    ) { backStackEntry ->
        LaunchedEffect(Unit) {
            val name = backStackEntry.arguments?.getString("name") ?: ""
            SearchBarState.onInput(name)
            navHostController.navigate(Routes.catalog) {
                popUpTo(backStackEntry.destination.id) { inclusive = true }
            }
            catalogViewModel.onEvent(CatalogEvent.SearchEvent.OnSearch)
        }
    }
}