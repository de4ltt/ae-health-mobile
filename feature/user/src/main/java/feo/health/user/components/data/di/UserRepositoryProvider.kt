package feo.health.user.components.data.di

import feo.health.user.components.domain.repository.IFavouritesRepository
import feo.health.user.components.domain.repository.IHistoryRepository
import feo.health.user.components.domain.repository.IUserRepository

interface UserRepositoryProvider {
    fun historyRepository(): IHistoryRepository
    fun userRepository(): IUserRepository
    fun favouriteRepository(): IFavouritesRepository
}
