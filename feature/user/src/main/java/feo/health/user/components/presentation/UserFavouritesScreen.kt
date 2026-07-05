package feo.health.user.components.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import feo.health.user.components.presentation.component.Favourites
import feo.health.user.components.presentation.viewmodel.UserViewModel
import feo.health.user.components.presentation.viewmodel.companion.UserEvent
import feo.health.user.components.presentation.viewmodel.companion.UserState

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
    BackHandler {
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
                Favourites.LoadingScreen()
            }
        }
    }
}
