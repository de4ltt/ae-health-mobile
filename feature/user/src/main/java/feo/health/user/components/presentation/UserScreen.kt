package feo.health.user.components.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import feo.health.network.endpoints.ApiEndpoints
import feo.health.ui.util.ILoading
import feo.health.user.components.presentation.component.Favourites
import feo.health.user.components.presentation.component.History
import feo.health.user.components.presentation.component.Profile
import feo.health.user.components.presentation.viewmodel.UserViewModel
import feo.health.user.components.presentation.viewmodel.companion.UserEvent
import feo.health.user.components.presentation.viewmodel.companion.UserState

@Composable
fun UserScreen(
    navHostController: NavHostController,
    userViewModel: UserViewModel
) {

    BackHandler {
        userViewModel.onEvent(UserEvent.OnBack)
    }

    val state by userViewModel.screenState.collectAsStateWithLifecycle()

    when (val screenState = state) {
        is UserState.Favourites.Default -> Favourites.Screen(
            favouriteItems = screenState.favourites,
            navHostController = navHostController,
            onEvent = userViewModel::onEvent
        )

        is UserState.History.Default -> History.Screen(
            historyItems = screenState.history,
            navHostController = navHostController,
            onEvent = userViewModel::onEvent
        )

        is UserState.Profile -> Profile.Screen(
            state = userViewModel.screenState,
            navHostController = navHostController,
            onEvent = userViewModel::onEvent
        )

        else -> (screenState as ILoading).LoadingScreen()
    }
}