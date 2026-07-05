package feo.health.user.components.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import feo.health.ui.navigation.Routes
import feo.health.ui.navigation.UserFavouritesRoute
import feo.health.ui.navigation.UserHistoryRoute
import feo.health.ui.navigation.UserRoute
import feo.health.user.components.presentation.UserScreen
import feo.health.user.components.presentation.component.Favourites
import feo.health.user.components.presentation.component.History
import feo.health.user.components.presentation.viewmodel.UserViewModel
import feo.health.user.components.presentation.viewmodel.companion.UserEvent
import feo.health.user.components.presentation.viewmodel.companion.UserState

/**
 * Configures the user feature navigation graph.
 * Sets up composable destinations for the main user profile screen, favourites, and history.
 *
 * @param navHostController The navigation controller used to coordinate navigation.
 * @param userViewModel The ViewModel instance shared across the user screen destinations.
 */
fun NavGraphBuilder.userNavGraph(
    navHostController: NavHostController,
    userViewModel: UserViewModel
) {
    // String-based route for compatibility
    composable(route = Routes.user) {
        UserScreen(
            navHostController = navHostController,
            userViewModel = userViewModel
        )
    }

    // Type-safe routes
    composable<UserRoute> {
        UserScreen(
            navHostController = navHostController,
            userViewModel = userViewModel
        )
    }

    composable<UserFavouritesRoute> {
        UserFavouritesScreen(
            navHostController = navHostController,
            userViewModel = userViewModel
        )
    }

    composable<UserHistoryRoute> {
        UserHistoryScreen(
            navHostController = navHostController,
            userViewModel = userViewModel
        )
    }
}

/**
 * Composable screen displaying the user's favourite items.
 * Observes the current favourites state from the [userViewModel] and triggers
 * navigation pop on back gestures.
 *
 * @param navHostController The navigation controller used to pop back or navigate elsewhere.
 * @param userViewModel The ViewModel instance used to fetch data and trigger events.
 */
@Composable
fun UserFavouritesScreen(
    navHostController: NavHostController,
    userViewModel: UserViewModel
) {
    androidx.activity.compose.BackHandler {
        userViewModel.onEvent(UserEvent.OnBack)
        navHostController.popBackStack()
    }

    val state by userViewModel.screenState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        when (val screenState = state) {
            is UserState.Favourites.Default -> {
                Favourites.Screen(
                    favouriteItems = screenState.favourites,
                    navHostController = navHostController,
                    onEvent = userViewModel::onEvent
                )
            }
            is UserState.Favourites.Loading -> {
                screenState.LoadingScreen()
            }
            else -> {
                feo.health.user.components.presentation.component.Favourites.LoadingScreen()
            }
        }
    }
}

/**
 * Composable screen displaying the user's viewing history.
 * Observes the current history state from the [userViewModel] and handles
 * hardware back button events.
 *
 * @param navHostController The navigation controller used to pop back or navigate elsewhere.
 * @param userViewModel The ViewModel instance used to fetch data and trigger events.
 */
@Composable
fun UserHistoryScreen(
    navHostController: NavHostController,
    userViewModel: UserViewModel
) {
    androidx.activity.compose.BackHandler {
        userViewModel.onEvent(UserEvent.OnBack)
        navHostController.popBackStack()
    }

    val state by userViewModel.screenState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        when (val screenState = state) {
            is UserState.History.Default -> {
                History.Screen(
                    historyItems = screenState.history,
                    navHostController = navHostController,
                    onEvent = userViewModel::onEvent
                )
            }
            is UserState.History.Loading -> {
                screenState.LoadingScreen()
            }
            else -> {
                feo.health.user.components.presentation.component.History.LoadingScreen()
            }
        }
    }
}