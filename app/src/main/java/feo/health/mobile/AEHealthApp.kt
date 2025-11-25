package feo.health.mobile

import android.app.Application
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import feo.health.ai.data.di.DaggerFeatureAiComponent
import feo.health.ai.data.di.FeatureAiComponent
import feo.health.ai.di.DaggerNetworkAIComponent
import feo.health.ai.di.NetworkAIComponent
import feo.health.ai.presentation.viewmodel.AiViewModel
import feo.health.auth.api.DummyRefreshApi
import feo.health.auth.data.di.DaggerFeatureAuthComponent
import feo.health.auth.data.di.FeatureAuthComponent
import feo.health.auth.di.DaggerNetworkAuthComponent
import feo.health.auth.di.NetworkAuthComponent
import feo.health.auth.presentation.viewmodel.AuthViewModel
import feo.health.catalog.data.di.DaggerFeatureCatalogComponent
import feo.health.catalog.data.di.FeatureCatalogComponent
import feo.health.catalog.di.DaggerNetworkCatalogComponent
import feo.health.catalog.di.NetworkCatalogComponent
import feo.health.catalog.presentation.viewmodel.CatalogViewModel
import feo.health.mobile.datastore.HDataStoreImpl
import feo.health.mobile.di.AppComponent
import feo.health.mobile.di.DaggerAppComponent
import feo.health.mobile.secrets.Secrets
import feo.health.mobile.viewmodel.ViewModelRegister
import feo.health.network.datastore.HDataStore
import feo.health.network.di.component.DaggerNetworkComponent
import feo.health.network.di.component.NetworkComponent
import feo.health.network.refresh_api.IRefreshApi
import feo.health.secrets.di.CoreSecretsComponent
import feo.health.secrets.di.DaggerCoreSecretsComponent
import feo.health.ui.image_loader.HImageLoader
import feo.health.user.components.data.di.DaggerFeatureUserComponent
import feo.health.user.components.data.di.FeatureUserComponent
import feo.health.user.components.presentation.viewmodel.UserViewModel
import feo.health.user.di.DaggerNetworkUserComponent
import feo.health.user.di.NetworkUserComponent

class AEHealthApp : Application() {

    lateinit var appComponent: AppComponent
    lateinit var dataStore: HDataStore
    lateinit var refreshApi: IRefreshApi

    lateinit var coreSecretsComponent: CoreSecretsComponent

    lateinit var networkComponent: NetworkComponent
    lateinit var networkAIComponent: NetworkAIComponent
    lateinit var networkCatalogComponent: NetworkCatalogComponent
    lateinit var networkAuthComponent: NetworkAuthComponent
    lateinit var networkUserComponent: NetworkUserComponent
    lateinit var featureCatalogComponent: FeatureCatalogComponent
    lateinit var featureAuthComponent: FeatureAuthComponent
    lateinit var featureUserComponent: FeatureUserComponent
    lateinit var featureAiComponent: FeatureAiComponent

    lateinit var imageLoader: ImageLoader

    override fun onCreate() {
        super.onCreate()
        initializeImageLoader()
        initializeCoreComponents()
        dataStore = HDataStoreImpl(this)
        initializeNetworkComponents()
        initializeFeatureComponents()
        appComponent = DaggerAppComponent.builder()
            .bindContext(this)
            .bindFeatureCatalogComponent(featureCatalogComponent)
            .bindFeatureAuthComponent(featureAuthComponent)
            .bindFeatureUserComponent(featureUserComponent)
            .bindFeatureAiComponent(featureAiComponent)
            .build()
        appComponent.inject(this)
        initializeViewModels()
    }

    private fun initializeCoreComponents() {
        coreSecretsComponent = DaggerCoreSecretsComponent.builder()
            .bindSecrets(Secrets)
            .build()
    }

    private fun initializeNetworkComponents() {
        networkComponent = DaggerNetworkComponent.builder()
            .bindDatastore(dataStore)
            .bindRefreshApi(DummyRefreshApi())
            .build()

        networkAuthComponent = DaggerNetworkAuthComponent.builder()
            .bindDatastore(dataStore)
            .bindNetworkComponent(networkComponent)
            .build()
        refreshApi = networkAuthComponent.refreshApi()

        networkComponent = DaggerNetworkComponent.builder()
            .bindDatastore(dataStore)
            .bindRefreshApi(refreshApi)
            .build()

        networkAIComponent = DaggerNetworkAIComponent.builder()
            .bindNetworkComponent(networkComponent)
            .build()
        networkCatalogComponent = DaggerNetworkCatalogComponent.builder()
            .bindNetworkComponent(networkComponent)
            .bindCoreSecretsComponent(coreSecretsComponent)
            .build()
        networkUserComponent = DaggerNetworkUserComponent.builder()
            .bindNetworkComponent(networkComponent)
            .bindDatastore(dataStore)
            .build()
    }

    private fun initializeFeatureComponents() {
        featureCatalogComponent = DaggerFeatureCatalogComponent.builder()
            .bindNetworkCatalogComponent(networkCatalogComponent)
            .build()
        featureAuthComponent = DaggerFeatureAuthComponent.builder()
            .bindNetworkAuthComponent(networkAuthComponent)
            .bindDataStore(dataStore)
            .build()
        featureUserComponent = DaggerFeatureUserComponent.builder()
            .bindNetworkUserComponent(networkUserComponent)
            .build()
        featureAiComponent = DaggerFeatureAiComponent.builder()
            .bindNetworkAiComponent(networkAIComponent)
            .build()
    }

    private fun initializeImageLoader() {
        imageLoader = ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(this, 0.5)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(this.cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.5)
                    .build()
            }
            .build()
        HImageLoader.INSTANCE = imageLoader
    }

    private fun initializeViewModels() {
        ViewModelRegister
            .register<CatalogViewModel>(
                appComponent.catalogViewModelFactory()
            ).register<AuthViewModel>(
                appComponent.authViewModelFactory()
            )
            .register<UserViewModel>(
                appComponent.userViewModelFactory()
            )
            .register<AiViewModel>(
                appComponent.aiViewModelFactory()
            )
    }
}
