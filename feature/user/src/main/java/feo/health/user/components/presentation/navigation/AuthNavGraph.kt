package feo.health.user.components.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import feo.health.ui.navigation.Routes
import feo.health.user.components.presentation.UserScreen
import feo.health.user.components.presentation.viewmodel.UserViewModel

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
}