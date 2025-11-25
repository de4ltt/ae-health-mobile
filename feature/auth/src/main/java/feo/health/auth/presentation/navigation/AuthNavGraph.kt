package feo.health.auth.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import feo.health.auth.presentation.AuthScreen
import feo.health.auth.presentation.viewmodel.AuthViewModel
import feo.health.auth.presentation.viewmodel.companion.AuthEvent
import feo.health.ui.navigation.Routes

fun NavGraphBuilder.authNavGraph(
    navHostController: NavHostController,
    authViewModel: AuthViewModel
) {
    composable(route = Routes.auth) {
        AuthScreen(
            navHostController = navHostController,
            authViewModel = authViewModel
        )
    }
    composable(route = Routes.authLogOut) { backStackEntry ->
        LaunchedEffect(Unit) {
            navHostController.navigate(Routes.auth) {
                popUpTo(backStackEntry.destination.id) { inclusive = true }
            }
        }
    }
}