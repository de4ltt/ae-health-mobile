package feo.health.network.di.component

import dagger.BindsInstance
import dagger.Component
import feo.health.common.model.datastore.IpStorage
import feo.health.network.di.NetworkModuleScope
import feo.health.network.di.module.EndpointsModule
import feo.health.network.endpoints.IApiEndpoints
import io.github.cdimascio.dotenv.Dotenv

@NetworkModuleScope
@Component(modules = [EndpointsModule::class])
interface CatalogEndpointsComponent {

    fun doctorEndpoints(): IApiEndpoints.Catalog.Doctor

    fun searchEndpoints(): IApiEndpoints.Catalog.Search

    fun clinicEndpoints(): IApiEndpoints.Catalog.Clinic

    fun diseaseEndpoints(): IApiEndpoints.Catalog.Disease

    fun drugEndpoints(): IApiEndpoints.Catalog.Drug

    fun servicesEndpoints(): IApiEndpoints.Catalog.Services

    fun pharmacyEndpoints(): IApiEndpoints.Catalog.Pharmacy

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindIpStorage(ipStorage: IpStorage): Builder

        @BindsInstance
        fun bindDotenv(dotenv: Dotenv): Builder

        fun build(): CatalogEndpointsComponent
    }
}