package feo.health.auth.di.module

import dagger.Binds
import dagger.Module
import feo.health.auth.di.NetworkAuthScope
import feo.health.auth.data.repository.AuthRepository
import feo.health.auth.domain.repository.IAuthRepository

@Module
internal abstract class RepositoryModule {

    @NetworkAuthScope
    @Binds
    abstract fun bindAuthRepository(authRepository: AuthRepository): IAuthRepository

}