package feo.health.mobile.di

import android.app.Application
import dagger.Component
import feo.health.catalog.data.di.FeatureCatalogComponent
import feo.health.catalog.presentation.viewmodel.CatalogViewModelFactory
import feo.health.datastore.datastore.di.DatastoreComponent
import io.github.cdimascio.dotenv.Dotenv

@AppScope
@Component(dependencies = [FeatureCatalogComponent::class])
interface AppComponent {

    fun inject(app: Application)

    fun catalogViewModelFactory(): CatalogViewModelFactory

    @Component.Builder
    interface Builder {

        fun bindFeatureCatalogComponent(featureCatalogComponent: FeatureCatalogComponent): Builder

        fun build(): AppComponent
    }
}