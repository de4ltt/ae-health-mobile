package feo.health.user.components.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import feo.health.ui.navigation.Routes
import feo.health.ui.navigation.UserFavouritesRoute
import feo.health.ui.navigation.UserHistoryRoute
import feo.health.ui.navigation.UserRoute
import feo.health.user.components.presentation.UserScreen
import feo.health.user.components.presentation.UserFavouritesScreen
import feo.health.user.components.presentation.UserHistoryScreen
import feo.health.user.components.presentation.viewmodel.UserViewModel


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
    composable(route = Routes.user) {
        UserScreen(
            navHostController = navHostController,
            userViewModel = userViewModel
        )
    }

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