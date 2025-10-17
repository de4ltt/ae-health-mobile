package feo.health.mobile

import android.app.Application
import feo.health.ai.di.DaggerNetworkAIComponent
import feo.health.ai.di.NetworkAIComponent
import feo.health.catalog.data.di.DaggerFeatureCatalogComponent
import feo.health.catalog.data.di.FeatureCatalogComponent
import feo.health.catalog.di.DaggerNetworkCatalogComponent
import feo.health.catalog.di.NetworkCatalogComponent
import feo.health.catalog.presentation.viewmodel.CatalogViewModel
import feo.health.datastore.datastore.di.DaggerDatastoreComponent
import feo.health.datastore.datastore.di.DatastoreComponent
import feo.health.mobile.di.AppComponent
import feo.health.mobile.di.DaggerAppComponent
import feo.health.mobile.viewmodel.ViewModelRegister
import feo.health.network.di.component.AIEndpointsComponent
import feo.health.network.di.component.AuthEndpointsComponent
import feo.health.network.di.component.CatalogEndpointsComponent
import feo.health.network.di.component.DaggerAIEndpointsComponent
import feo.health.network.di.component.DaggerAuthEndpointsComponent
import feo.health.network.di.component.DaggerCatalogEndpointsComponent
import feo.health.network.di.component.DaggerNetworkComponent
import feo.health.network.di.component.DaggerUserEndpointsComponent
import feo.health.network.di.component.NetworkComponent
import feo.health.network.di.component.UserEndpointsComponent

class AEHealthApp : Application() {

    lateinit var appComponent: AppComponent

    lateinit var datastoreComponent: DatastoreComponent

    lateinit var networkComponent: NetworkComponent

    lateinit var aiEndpointsComponent: AIEndpointsComponent
    lateinit var authEndpointsComponent: AuthEndpointsComponent
    lateinit var catalogEndpointsComponent: CatalogEndpointsComponent
    lateinit var userEndpointsComponent: UserEndpointsComponent

    lateinit var networkAIComponent: NetworkAIComponent
    lateinit var networkCatalogComponent: NetworkCatalogComponent

    lateinit var featureCatalogComponent: FeatureCatalogComponent

    override fun onCreate() {
        super.onCreate()

        initializeCoreComponents()
        initializeNetworkComponents()
        initializeFeatureComponents()

        appComponent = DaggerAppComponent.builder()
            .bindFeatureCatalogComponent(featureCatalogComponent)
            .build()
        appComponent.inject(this)

        initializeViewModels()
    }

    private fun initializeCoreComponents() {
        datastoreComponent = DaggerDatastoreComponent.builder()
            .bindApp(this)
            .bindContext(this)
            .build()
    }

    private fun initializeNetworkComponents() {
        networkComponent = DaggerNetworkComponent.builder()
            .bindDotenv(datastoreComponent.dotenv())
            .bindIpStorage(datastoreComponent.ipStorage())
            .build()

        aiEndpointsComponent = DaggerAIEndpointsComponent.builder()
            .bindIpStorage(datastoreComponent.ipStorage())
            .bindDotenv(datastoreComponent.dotenv())
            .build()
        authEndpointsComponent = DaggerAuthEndpointsComponent.builder()
            .bindIpStorage(datastoreComponent.ipStorage())
            .bindDotenv(datastoreComponent.dotenv())
            .build()
        catalogEndpointsComponent = DaggerCatalogEndpointsComponent.builder()
            .bindIpStorage(datastoreComponent.ipStorage())
            .bindDotenv(datastoreComponent.dotenv())
            .build()
        userEndpointsComponent = DaggerUserEndpointsComponent.builder()
            .bindIpStorage(datastoreComponent.ipStorage())
            .bindDotenv(datastoreComponent.dotenv())
            .build()

        networkAIComponent = DaggerNetworkAIComponent.builder()
            .bindNetworkComponent(networkComponent)
            .bindAIEndpointsComponent(aiEndpointsComponent)
            .build()
        networkCatalogComponent = DaggerNetworkCatalogComponent.builder()
            .bindNetworkComponent(networkComponent)
            .bindCatalogEndpointsComponent(catalogEndpointsComponent)
            .build()
    }

    private fun initializeFeatureComponents() {
        featureCatalogComponent = DaggerFeatureCatalogComponent.builder()
            .bindNetworkCatalogComponent(networkCatalogComponent)
            .build()
    }

    private fun initializeViewModels() {
        ViewModelRegister
            .register<CatalogViewModel>(appComponent.catalogViewModelFactory())
    }
}