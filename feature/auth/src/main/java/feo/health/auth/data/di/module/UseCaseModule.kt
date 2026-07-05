package feo.health.auth.data.di.module

import dagger.Module
import dagger.Provides
import feo.health.auth.data.di.FeatureAuthScope
import feo.health.auth.domain.repository.IAuthRepository
import feo.health.auth.domain.use_case.SignInUseCase
import feo.health.auth.domain.use_case.SignUpUseCase

/**
 * Dagger module providing authentication use case helper workflow instance dependencies.
 */
@Module
object UseCaseModule {

    /**
     * Provides the [SignInUseCase] instance.
     *
     * @param authRepository Authentication repository client.
     * @return User sign in usecase.
     */
    @FeatureAuthScope
    @Provides
    fun provideSignInUseCase(authRepository: IAuthRepository): SignInUseCase = SignInUseCase(
        authRepository
    )

    /**
     * Provides the [SignUpUseCase] instance.
     *
     * @param authRepository Authentication repository client.
     * @return User sign up usecase.
     */
    @FeatureAuthScope
    @Provides
    fun provideSignUpUseCase(authRepository: IAuthRepository): SignUpUseCase = SignUpUseCase(
        authRepository
    )
}