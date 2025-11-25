package feo.health.auth.data.di

import dagger.BindsInstance
import dagger.Component
import feo.health.auth.data.di.module.CatalogModule
import feo.health.auth.data.di.module.RepositoryModule
import feo.health.auth.data.di.module.UseCaseModule
import feo.health.auth.di.NetworkAuthComponent
import feo.health.auth.presentation.viewmodel.AuthViewModelFactory
import feo.health.network.datastore.HDataStore

@FeatureAuthScope
@Component(
    modules = [CatalogModule::class, RepositoryModule::class, UseCaseModule::class],
    dependencies = [NetworkAuthComponent::class]
)
interface FeatureAuthComponent {

    fun authViewModelFactory(): AuthViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindDataStore(dataStore: HDataStore): Builder

        fun bindNetworkAuthComponent(networkAuthComponent: NetworkAuthComponent): Builder

        fun build(): FeatureAuthComponent
    }
}