package feo.health.auth.data.di.module

import dagger.Module
import dagger.Provides
import feo.health.auth.data.di.FeatureAuthScope
import feo.health.auth.domain.repository.IAuthRepository
import feo.health.auth.domain.use_case.SignInUseCase
import feo.health.auth.domain.use_case.SignUpUseCase

@Module
object UseCaseModule {

    @FeatureAuthScope
    @Provides
    fun provideSignInUseCase(authRepository: IAuthRepository): SignInUseCase = SignInUseCase(
        authRepository
    )

    @FeatureAuthScope
    @Provides
    fun provideSignUpUseCase(authRepository: IAuthRepository): SignUpUseCase = SignUpUseCase(
        authRepository
    )

}