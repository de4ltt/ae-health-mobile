package feo.health.user.components.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import feo.health.ui.navigation.UserFavouritesRoute
import feo.health.ui.navigation.UserHistoryRoute
import feo.health.ui.util.ILoading
import feo.health.user.components.presentation.component.Profile
import feo.health.user.components.presentation.viewmodel.UserViewModel
import feo.health.user.components.presentation.viewmodel.companion.UserEvent
import feo.health.user.components.presentation.viewmodel.companion.UserState

/**
 * Composable screen component for the user feature.
 * Observes the screen state from the [userViewModel] and decides which sub-screen
 * or component to render based on the current state (e.g., [Profile.Screen] or a loading screen).
 * Handles user feature events and manages navigating to favourites or history screens.
 *
 * @param navHostController The navigation controller used for navigating between screens.
 * @param userViewModel The ViewModel storing the state and handling events for the user feature.
 */
@Composable
fun UserScreen(
    navHostController: NavHostController,
    userViewModel: UserViewModel
) {
    val state by userViewModel.screenState.collectAsStateWithLifecycle()

    when (val screenState = state) {
        is UserState.Profile -> Profile.Screen(
            state = userViewModel.screenState,
            navHostController = navHostController,
            onEvent = { event ->
                if (event is UserEvent.Favourites.OnRefresh) {
                    userViewModel.onEvent(event)
                    navHostController.navigate(UserFavouritesRoute)
                } else if (event is UserEvent.History.OnRefresh) {
                    userViewModel.onEvent(event)
                    navHostController.navigate(UserHistoryRoute)
                } else {
                    userViewModel.onEvent(event)
                }
            }
        )

        else -> {
            if (screenState is ILoading) {
                screenState.LoadingScreen()
            }
        }
    }
}