package feo.health.catalog.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import feo.health.catalog.presentation.CatalogScreen
import feo.health.catalog.presentation.CatalogDetailsScreen
import feo.health.catalog.presentation.CatalogSpecialistsScreen
import feo.health.catalog.presentation.viewmodel.CatalogViewModel
import feo.health.catalog.presentation.viewmodel.companion.CatalogEvent
import feo.health.catalog.presentation.viewmodel.companion.SearchBarState
import feo.health.ui.navigation.CatalogDetailsRoute
import feo.health.ui.navigation.CatalogSpecialistsRoute
import feo.health.ui.navigation.Routes
import kotlinx.serialization.Serializable


/**
 * Serializable object representing the base catalog route in the navigation system.
 */
@Serializable
object CatalogBaseRoute

/**
 * Sets up the navigation destinations and composables for the catalog feature graph.
 *
 * @param navHostController Navigation controller for driving flow and back stack operations.
 * @param catalogViewModel Common ViewModel holding UI states and dispatching actions.
 */
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

    composable<CatalogDetailsRoute> { backStackEntry ->
        val route: CatalogDetailsRoute = backStackEntry.toRoute()
        CatalogDetailsScreen(
            navHostController = navHostController,
            catalogViewModel = catalogViewModel,
            link = route.link,
            type = route.type
        )
    }

    composable<CatalogSpecialistsRoute> { backStackEntry ->
        val route: CatalogSpecialistsRoute = backStackEntry.toRoute()
        CatalogSpecialistsScreen(
            navHostController = navHostController,
            catalogViewModel = catalogViewModel,
            link = route.link
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