package feo.health.catalog.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.di.NetworkCatalogScope
import feo.health.catalog.services.api.IServicesApi
import feo.health.catalog.services.api.ServicesApi

/**
 * Dagger module binding the remote medical services API client implementation to its contract interface.
 */
@Module
internal abstract class ServicesModule {

    /**
     * Binds the medical services API client.
     *
     * @param servicesApi Concrete api implementation.
     * @return Bounded interface.
     */
    @NetworkCatalogScope
    @Binds
    abstract fun bindServicesApi(servicesApi: ServicesApi): IServicesApi
}