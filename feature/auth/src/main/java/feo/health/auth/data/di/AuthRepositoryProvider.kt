package feo.health.auth.data.di

import feo.health.auth.domain.repository.IAuthRepository

interface AuthRepositoryProvider {
    fun authRepository(): IAuthRepository
}
