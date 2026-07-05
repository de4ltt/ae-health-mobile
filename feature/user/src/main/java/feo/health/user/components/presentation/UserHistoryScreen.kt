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
import feo.health.user.components.presentation.component.History
import feo.health.user.components.presentation.viewmodel.UserViewModel
import feo.health.user.components.presentation.viewmodel.companion.UserEvent
import feo.health.user.components.presentation.viewmodel.companion.UserState

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
    BackHandler {
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
                History.LoadingScreen()
            }
        }
    }
}
