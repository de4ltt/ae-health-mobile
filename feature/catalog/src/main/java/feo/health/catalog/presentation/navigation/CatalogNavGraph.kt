package feo.health.catalog.presentation.navigation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import feo.health.catalog.presentation.CatalogScreen
import feo.health.catalog.presentation.component.Specialists
import feo.health.catalog.presentation.viewmodel.CatalogViewModel
import feo.health.catalog.presentation.viewmodel.companion.CatalogEvent
import feo.health.catalog.presentation.viewmodel.companion.CatalogState
import feo.health.catalog.presentation.viewmodel.companion.SearchBarState
import feo.health.ui.navigation.Routes
import feo.health.ui.navigation.CatalogDetailsRoute
import feo.health.ui.navigation.CatalogSpecialistsRoute
import feo.health.ui.util.ILoading
import kotlinx.serialization.Serializable

@Serializable
object CatalogBaseRoute

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

@Composable
fun CatalogDetailsScreen(
    navHostController: NavHostController,
    catalogViewModel: CatalogViewModel,
    link: String,
    type: String
) {
    androidx.activity.compose.BackHandler {
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

@Composable
fun CatalogSpecialistsScreen(
    navHostController: NavHostController,
    catalogViewModel: CatalogViewModel,
    link: String
) {
    androidx.activity.compose.BackHandler {
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