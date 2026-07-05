package feo.health.user.components.data.di

import dagger.Component
import feo.health.user.components.data.di.module.UseCaseModule
import feo.health.user.components.data.di.module.UserModule
import feo.health.user.components.domain.repository.IFavouritesRepository
import feo.health.user.components.domain.repository.IHistoryRepository
import feo.health.user.components.domain.repository.IUserRepository
import feo.health.user.components.domain.use_case.util.IFavouriteUseCases
import feo.health.user.components.domain.use_case.util.IHistoryUseCases
import feo.health.user.components.domain.use_case.util.IUserUseCases
import feo.health.user.components.presentation.viewmodel.UserViewModelFactory

/**
 * Dagger component for the Feature User module.
 *
 * This component provides dependencies required by the user feature, including view model factories,
 * repositories, and use cases, scoped to [FeatureUserScope].
 */
@FeatureUserScope
@Component(
    modules = [UserModule::class, UseCaseModule::class],
    dependencies = [UserRepositoryProvider::class]
)
interface FeatureUserComponent {

    /**
     * Provides the [UserViewModelFactory] instance.
     *
     * @return The factory to create user-related view models.
     */
    fun userViewModelFactory(): UserViewModelFactory

    /**
     * Provides the [IHistoryRepository] instance.
     *
     * @return The repository managing history data.
     */
    fun historyRepository(): IHistoryRepository

    /**
     * Provides the [IUserRepository] instance.
     *
     * @return The repository managing user profile and authentication data.
     */
    fun userRepository(): IUserRepository

    /**
     * Provides the [IFavouritesRepository] instance.
     *
     * @return The repository managing favourite catalog items.
     */
    fun favouriteRepository(): IFavouritesRepository

    /**
     * Provides the [IHistoryUseCases] instance.
     *
     * @return The container for history-related use cases.
     */
    fun historyUseCases(): IHistoryUseCases

    /**
     * Provides the [IUserUseCases] instance.
     *
     * @return The container for user-related use cases.
     */
    fun userUseCases(): IUserUseCases

    /**
     * Provides the [IFavouriteUseCases] instance.
     *
     * @return The container for favourite-related use cases.
     */
    fun favouriteUseCases(): IFavouriteUseCases

    /**
     * Builder interface for [FeatureUserComponent].
     */
    @Component.Builder
    interface Builder {

        /**
         * Binds the [UserRepositoryProvider] dependency to the component.
         *
         * @param userRepositoryProvider The provider supplying repository dependencies.
         * @return The [Builder] instance for method chaining.
         */
        fun bindUserRepositoryProvider(userRepositoryProvider: UserRepositoryProvider): Builder

        /**
         * Builds and returns the [FeatureUserComponent] instance.
         *
         * @return The fully constructed [FeatureUserComponent].
         */
        fun build(): FeatureUserComponent
    }
}