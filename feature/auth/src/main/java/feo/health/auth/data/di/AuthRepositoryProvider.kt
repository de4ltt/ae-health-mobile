package feo.health.auth.data.di

import feo.health.auth.domain.repository.IAuthRepository

/**
 * Interface contract providing a resolution point for obtaining the concrete [IAuthRepository] instance.
 */
interface AuthRepositoryProvider {
    /**
     * Resolves the configured [IAuthRepository] instance.
     *
     * @return Authentication remote services repository.
     */
    fun authRepository(): IAuthRepository
}
