package feo.health.auth.presentation.viewmodel.companion

sealed interface AuthEvent {

    data object OnSignUp: AuthEvent
    data object OnSignIn: AuthEvent

    data object OnSwitchScreen: AuthEvent

    data object OnBack: AuthEvent
}