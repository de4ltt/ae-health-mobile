package feo.health.auth.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import feo.health.auth.presentation.component.Authorization
import feo.health.auth.presentation.viewmodel.AuthViewModel
import feo.health.auth.presentation.viewmodel.companion.AuthEvent
import feo.health.auth.presentation.viewmodel.companion.AuthState
import feo.health.ui.component.NavAnchors
import feo.health.ui.navigation.Routes

/**
 * Main UI Composable screen routing logins authentication flow and profile registration pages templates.
 *
 * @param navHostController Router navigation controller interface helper.
 * @param authViewModel View model containing screen states and event processors.
 */
@Composable
fun AuthScreen(
    navHostController: NavHostController,
    authViewModel: AuthViewModel
) {

    BackHandler {
        if (authViewModel.isEmpty)
            navHostController.popBackStack()
        else authViewModel.onEvent(AuthEvent.OnBack)
    }

    LaunchedEffect(Unit) {
        NavAnchors.hide()
    }

    val screenState by authViewModel.screenState.collectAsStateWithLifecycle()

    val isSignUpState = screenState is AuthState.SignUp

    AnimatedContent(
        targetState = isSignUpState
    ) {
        if (it)
            Authorization.SignUpScreen(
                screenState = authViewModel.screenState,
                onEvent = authViewModel::onEvent
            )
        else
            Authorization.SignInScreen(
                screenState = authViewModel.screenState,
                onEvent = authViewModel::onEvent
            )
    }

    LaunchedEffect(screenState) {
        if (screenState is AuthState.SignIn.Authorized) {
            navHostController.navigate(Routes.catalog) {
                popUpTo(Routes.auth) {
                    inclusive = true
                }
                launchSingleTop = true
            }
            authViewModel.onEvent(AuthEvent.OnBack)
        }
    }
}