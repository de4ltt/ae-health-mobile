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
import feo.health.ui.navigation.UserRoute
import feo.health.ui.navigation.UserFavouritesRoute
import feo.health.ui.navigation.UserHistoryRoute
import feo.health.ui.util.ILoading
import feo.health.user.components.presentation.UserScreen
import feo.health.user.components.presentation.component.Favourites
import feo.health.user.components.presentation.component.History
import feo.health.user.components.presentation.viewmodel.UserViewModel
import feo.health.user.components.presentation.viewmodel.companion.UserEvent
import feo.health.user.components.presentation.viewmodel.companion.UserState
import kotlinx.serialization.Serializable

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