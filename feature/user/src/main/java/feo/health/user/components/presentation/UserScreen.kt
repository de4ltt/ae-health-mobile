package feo.health.user.components.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import feo.health.ui.util.ILoading
import feo.health.user.components.presentation.component.Profile
import feo.health.ui.navigation.UserFavouritesRoute
import feo.health.ui.navigation.UserHistoryRoute
import feo.health.user.components.presentation.viewmodel.UserViewModel
import feo.health.user.components.presentation.viewmodel.companion.UserEvent
import feo.health.user.components.presentation.viewmodel.companion.UserState

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