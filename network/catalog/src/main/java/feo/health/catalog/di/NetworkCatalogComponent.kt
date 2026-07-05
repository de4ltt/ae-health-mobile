package feo.health.catalog.di

import dagger.Component
import feo.health.catalog.clinic.api.IClinicApi
import feo.health.catalog.data.di.CatalogRepositoryProvider
import feo.health.catalog.di.module.ClinicModule
import feo.health.catalog.di.module.DiseaseModule
import feo.health.catalog.di.module.DoctorModule
import feo.health.catalog.di.module.DrugModule
import feo.health.catalog.di.module.PharmacyModule
import feo.health.catalog.di.module.RepositoryModule
import feo.health.catalog.di.module.SearchModule
import feo.health.catalog.di.module.ServicesModule
import feo.health.catalog.disease.api.IDiseaseApi
import feo.health.catalog.doctor.api.IDoctorApi
import feo.health.catalog.drug.api.IDrugApi
import feo.health.catalog.pharmacy.api.IPharmacyApi
import feo.health.catalog.search.api.ISearchApi
import feo.health.catalog.services.api.IServicesApi
import feo.health.network.di.component.NetworkComponent
import feo.health.secrets.di.CoreSecretsComponent

/**
 * Dagger dependency injection component providing catalog remote APIs and repository instances.
 */
@NetworkCatalogScope
@Component(
    modules = [
        ClinicModule::class, DiseaseModule::class, DoctorModule::class, DrugModule::class,
        PharmacyModule::class, SearchModule::class, ServicesModule::class, RepositoryModule::class
    ],
    dependencies = [NetworkComponent::class, CoreSecretsComponent::class]
)
interface NetworkCatalogComponent : CatalogRepositoryProvider {

    /**
     * Resolves the clinic search API interface dependency.
     *
     * @return Configured [IClinicApi].
     */
    fun clinicApi(): IClinicApi

    /**
     * Resolves the disease directory API interface dependency.
     *
     * @return Configured [IDiseaseApi].
     */
    fun diseaseApi(): IDiseaseApi

    /**
     * Resolves the doctor specialty API interface dependency.
     *
     * @return Configured [IDoctorApi].
     */
    fun doctorApi(): IDoctorApi

    /**
     * Resolves the drug medications API interface dependency.
     *
     * @return Configured [IDrugApi].
     */
    fun drugApi(): IDrugApi

    /**
     * Resolves the pharmacy locations API interface dependency.
     *
     * @return Configured [IPharmacyApi].
     */
    fun pharmacyApi(): IPharmacyApi

    /**
     * Resolves the main search matching API interface dependency.
     *
     * @return Configured [ISearchApi].
     */
    fun searchApi(): ISearchApi

    /**
     * Resolves the services directory API interface dependency.
     *
     * @return Configured [IServicesApi].
     */
    fun servicesApi(): IServicesApi

    /**
     * Dagger component builder for assembling the [NetworkCatalogComponent].
     */
    @Component.Builder
    interface Builder {

        /**
         * Hooks the core local secrets component dependency.
         *
         * @param coreSecretsComponent Local encryption keys provider.
         * @return Dagger builder instance.
         */
        fun bindCoreSecretsComponent(coreSecretsComponent: CoreSecretsComponent): Builder

        /**
         * Hooks the core network component dependencies.
         *
         * @param networkComponent Core HTTP client configs provider.
         * @return Dagger builder instance.
         */
        fun bindNetworkComponent(networkComponent: NetworkComponent): Builder

        /**
         * Builds and returns the [NetworkCatalogComponent].
         *
         * @return Initialized component instance.
         */
        fun build(): NetworkCatalogComponent
    }
}