package feo.health.catalog.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.di.NetworkCatalogScope
import feo.health.catalog.services.api.IServicesApi
import feo.health.catalog.services.api.ServicesApi

@Module
internal abstract class ServicesModule {

    @NetworkCatalogScope
    @Binds
    abstract fun bindServicesApi(servicesApi: ServicesApi): IServicesApi

}