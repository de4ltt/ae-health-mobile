package feo.health.auth.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import feo.health.auth.domain.model.SignInDomain
import feo.health.auth.domain.model.SignUpDomain
import feo.health.auth.domain.use_case.SignInUseCase
import feo.health.auth.domain.use_case.SignUpUseCase
import feo.health.auth.presentation.viewmodel.companion.AuthEvent
import feo.health.auth.presentation.viewmodel.companion.AuthFieldsState
import feo.health.auth.presentation.viewmodel.companion.AuthState
import feo.health.ui.component.HToast
import feo.health.ui.component.HToast.tryWithToast
import feo.health.ui.resource.HStrings
import feo.health.ui.viewmodel.HViewModel
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : HViewModel<AuthState, AuthEvent>(initialState = AuthState.SignIn.Default) {

    override fun onEvent(event: AuthEvent) = when (event) {
        AuthEvent.OnBack -> onBack()
        AuthEvent.OnSignIn -> onSignIn()
        AuthEvent.OnSignUp -> onSignUp()
        AuthEvent.OnSwitchScreen -> onSwitchScreen()
    }

    private fun onSignIn() = viewModelScope.tryWithToast(
        successMessageRequired = true,
        onError = { updateScreenState(AuthState.SignIn.Default) }

    ) {
        updateScreenState(AuthState.SignIn.Loading)
        val request = SignInDomain(
            email = AuthFieldsState.SignIn.email.value,
            password = AuthFieldsState.SignIn.password.value
        )
        signInUseCase(request)
        updateScreenState(AuthState.SignIn.Authorized)
    }

    private fun onSignUp() = viewModelScope.tryWithToast(
        successMessageRequired = true,
        onError = { updateScreenState(AuthState.SignUp.Default) }
    ) {
        updateScreenState(AuthState.SignUp.Loading)
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val request = SignUpDomain(
            email = AuthFieldsState.SignUp.email.value,
            password = AuthFieldsState.SignUp.password.value,
            dateOfBirth = LocalDate.parse(AuthFieldsState.SignUp.dateOfBirth.value, formatter),
            name = AuthFieldsState.SignUp.name.value
        )
        signUpUseCase(request)
        updateScreenState(AuthState.SignIn.Default)
    }

    private fun onSwitchScreen() = viewModelScope.tryWithToast {
        if (screenState.value is AuthState.SignIn.Default)
            updateScreenState(AuthState.SignUp.Default)
        else if (screenState.value is AuthState.SignUp.Default)
            updateScreenState(AuthState.SignIn.Default)
    }
}