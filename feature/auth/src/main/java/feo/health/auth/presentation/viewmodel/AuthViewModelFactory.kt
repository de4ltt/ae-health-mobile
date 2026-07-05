package feo.health.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import feo.health.auth.domain.use_case.SignInUseCase
import feo.health.auth.domain.use_case.SignUpUseCase
import javax.inject.Inject

/**
 * Custom Factory class responsible for initializing [AuthViewModel] instances with correct dependencies.
 * Extends [ViewModelProvider.Factory] interface contract.
 *
 * @property signInUseCase Sign in credentials authentication check workflow.
 * @property signUpUseCase Registration sign up account creation workflow.
 */
class AuthViewModelFactory @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModelProvider.Factory {

    /**
     * Instantiates the requested ViewModel type subclass.
     *
     * @param modelClass The class type representation to instantiate.
     * @return Initialized ViewModel subclass casting instance.
     * @throws IllegalArgumentException If an unsupported ViewModel class type is passed.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(
                signInUseCase = signInUseCase,
                signUpUseCase = signUpUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}