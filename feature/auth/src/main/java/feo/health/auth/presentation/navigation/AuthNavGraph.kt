package feo.health.auth.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import feo.health.auth.presentation.AuthScreen
import feo.health.auth.presentation.viewmodel.AuthViewModel
import feo.health.ui.navigation.AuthLogOutRoute
import feo.health.ui.navigation.AuthRoute
import feo.health.ui.navigation.Routes

/**
 * Extension on [NavGraphBuilder] configuring route navigation mappings targeting the authentication screens.
 * Handles both string-based and type-safe route matching layouts.
 *
 * @param navHostController Router navigation controller interface helper.
 * @param authViewModel View model containing screen states and event processors.
 */
fun NavGraphBuilder.authNavGraph(
    navHostController: NavHostController,
    authViewModel: AuthViewModel
) {
    // String-based route for compatibility
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

    // Type-safe routes
    composable<AuthRoute> {
        AuthScreen(
            navHostController = navHostController,
            authViewModel = authViewModel
        )
    }

    composable<AuthLogOutRoute> { backStackEntry ->
        LaunchedEffect(Unit) {
            navHostController.navigate(Routes.auth) {
                popUpTo(backStackEntry.destination.id) { inclusive = true }
            }
        }
    }
}