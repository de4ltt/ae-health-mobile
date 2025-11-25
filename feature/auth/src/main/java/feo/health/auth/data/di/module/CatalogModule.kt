package feo.health.auth.data.di.module

import dagger.Module
import dagger.Provides
import feo.health.auth.data.di.FeatureAuthScope
import feo.health.auth.domain.use_case.SignInUseCase
import feo.health.auth.domain.use_case.SignUpUseCase
import feo.health.auth.presentation.viewmodel.AuthViewModelFactory

@Module
internal class CatalogModule {

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