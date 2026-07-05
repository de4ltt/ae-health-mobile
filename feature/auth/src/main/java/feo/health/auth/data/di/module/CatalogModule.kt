package feo.health.auth.data.di.module

import dagger.Module
import dagger.Provides
import feo.health.auth.data.di.FeatureAuthScope
import feo.health.auth.domain.use_case.SignInUseCase
import feo.health.auth.domain.use_case.SignUpUseCase
import feo.health.auth.presentation.viewmodel.AuthViewModelFactory

/**
 * Dagger module providing authentication ViewModel Factory instance structures.
 */
@Module
internal class CatalogModule {

    /**
     * Provides the factory instance required to construct [AuthViewModel].
     *
     * @param signInUseCase User sign in authentication workflow.
     * @param signUpUseCase User sign up account registration workflow.
     * @return Concrete view model factory instance.
     */
    @FeatureAuthScope
    @Provides
    fun provideSearchViewModelFactory(
        signInUseCase: SignInUseCase,
        signUpUseCase: SignUpUseCase
    ): AuthViewModelFactory = AuthViewModelFactory(
        signUpUseCase = signUpUseCase,
        signInUseCase = signInUseCase
    )
}