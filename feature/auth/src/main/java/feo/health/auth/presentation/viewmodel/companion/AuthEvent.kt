package feo.health.auth.presentation.viewmodel.companion

/**
 * Sealed interface representing UI interaction events triggered on the credentials login/registration screen.
 */
sealed interface AuthEvent {

    /**
     * Event triggered to submit new user registration sign up details.
     */
    data object OnSignUp: AuthEvent

    /**
     * Event triggered to submit sign in login credentials verification check.
     */
    data object OnSignIn: AuthEvent

    /**
     * Event triggered to switch layouts context between login and registration layouts.
     */
    data object OnSwitchScreen: AuthEvent

    /**
     * Event triggered when navigation back stack actions occur.
     */
    data object OnBack: AuthEvent
}