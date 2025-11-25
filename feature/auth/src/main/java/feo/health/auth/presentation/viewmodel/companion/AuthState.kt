package feo.health.auth.presentation.viewmodel.companion

import androidx.compose.runtime.Composable
import feo.health.ui.util.ILoading

sealed class AuthState {

    sealed class SignIn: AuthState() {
        data object Default : SignIn()
        data object Loading : SignIn(), ILoading {
            @Composable
            override fun LoadingScreen(vararg params: Any) {
                TODO("Not yet implemented")
            }
        }
        data object Authorized: SignIn()
    }

    sealed class SignUp: AuthState() {
        data object Default: SignUp()
        data object Loading: SignUp(), ILoading {
            @Composable
            override fun LoadingScreen(vararg params: Any) {
                TODO("Not yet implemented")
            }
        }
    }
}