package feo.health.auth.di.module

import dagger.Binds
import dagger.Module
import feo.health.auth.data.repository.AuthRepository
import feo.health.auth.di.NetworkAuthScope
import feo.health.auth.domain.repository.IAuthRepository

/**
 * Dagger module binding the remote auth repository implementation to its contract interface.
 */
@Module
internal abstract class RepositoryModule {

    /**
     * Binds the authentication repository service.
     *
     * @param authRepository Concrete repository implementation.
     * @return Bounded interface.
     */
    @NetworkAuthScope
    @Binds
    abstract fun bindAuthRepository(authRepository: AuthRepository): IAuthRepository
}