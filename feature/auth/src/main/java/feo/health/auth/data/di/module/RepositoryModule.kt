package feo.health.auth.data.di.module

import dagger.Binds
import dagger.Module
import feo.health.auth.data.di.FeatureAuthScope
import feo.health.auth.data.repository.AuthRepository
import feo.health.auth.domain.repository.IAuthRepository

@Module
internal abstract class RepositoryModule {

    @FeatureAuthScope
    @Binds
    abstract fun bindAuthRepository(authRepository: AuthRepository): IAuthRepository

}