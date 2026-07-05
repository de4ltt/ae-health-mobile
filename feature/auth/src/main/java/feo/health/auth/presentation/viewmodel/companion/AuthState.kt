package feo.health.auth.presentation.viewmodel.companion

import androidx.compose.runtime.Composable
import feo.health.ui.util.ILoading

/**
 * Sealed representation of potential UI rendering states for the login and registration screen.
 */
sealed class AuthState {

    /**
     * Grouping of potential states during user sign in.
     */
    sealed class SignIn: AuthState() {
        /**
         * Initial resting login form state.
         */
        data object Default : SignIn()

        /**
         * Loading state while processing sign in login queries.
         */
        data object Loading : SignIn(), ILoading {
            /**
             * Screen rendering placeholder method for loading state.
             */
            @Composable
            override fun LoadingScreen(vararg params: Any) {
                TODO("Not yet implemented")
            }
        }

        /**
         * Success state confirming successful sign in verification check.
         */
        data object Authorized: SignIn()
    }

    /**
     * Grouping of potential states during user registration sign up.
     */
    sealed class SignUp: AuthState() {
        /**
         * Initial resting sign up form state.
         */
        data object Default: SignUp()

        /**
         * Loading state while processing new account creation queries.
         */
        data object Loading: SignUp(), ILoading {
            /**
             * Screen rendering placeholder method for loading state.
             */
            @Composable
            override fun LoadingScreen(vararg params: Any) {
                TODO("Not yet implemented")
            }
        }
    }
}