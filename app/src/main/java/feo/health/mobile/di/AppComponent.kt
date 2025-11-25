package feo.health.mobile.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import feo.health.ai.data.di.FeatureAiComponent
import feo.health.ai.presentation.viewmodel.AiViewModelFactory
import feo.health.auth.data.di.FeatureAuthComponent
import feo.health.auth.presentation.viewmodel.AuthViewModelFactory
import feo.health.catalog.data.di.FeatureCatalogComponent
import feo.health.catalog.presentation.viewmodel.CatalogViewModelFactory
import feo.health.mobile.di.module.DataStoreModule
import feo.health.network.datastore.HDataStore
import feo.health.user.components.data.di.FeatureUserComponent
import feo.health.user.components.presentation.viewmodel.UserViewModelFactory

@AppScope
@Component(
    dependencies = [FeatureCatalogComponent::class, FeatureAuthComponent::class, FeatureUserComponent::class, FeatureAiComponent::class],
    modules = [DataStoreModule::class]
)
interface AppComponent {

    fun inject(app: Application)

    fun catalogViewModelFactory(): CatalogViewModelFactory

    fun authViewModelFactory(): AuthViewModelFactory

    fun userViewModelFactory(): UserViewModelFactory

    fun aiViewModelFactory(): AiViewModelFactory

    fun dataStore(): HDataStore

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindContext(context: Context): Builder

        fun bindFeatureCatalogComponent(featureCatalogComponent: FeatureCatalogComponent?): Builder

        fun bindFeatureAuthComponent(featureAuthComponent: FeatureAuthComponent): Builder

        fun bindFeatureUserComponent(featureUserComponent: FeatureUserComponent): Builder

        fun bindFeatureAiComponent(featureAiComponent: FeatureAiComponent): Builder

        fun build(): AppComponent
    }
}