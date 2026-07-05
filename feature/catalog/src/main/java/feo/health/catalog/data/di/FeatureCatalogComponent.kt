package feo.health.catalog.data.di

import dagger.Component
import feo.health.catalog.data.di.module.CatalogModule
import feo.health.catalog.data.di.module.UseCaseModule
import feo.health.catalog.domain.repository.IClinicRepository
import feo.health.catalog.domain.repository.IDiseaseRepository
import feo.health.catalog.domain.repository.IDoctorRepository
import feo.health.catalog.domain.repository.IDrugRepository
import feo.health.catalog.domain.repository.IPharmacyRepository
import feo.health.catalog.domain.repository.ISearchRepository
import feo.health.catalog.domain.repository.IServicesRepository
import feo.health.catalog.domain.use_case.util.ICatalogUseCases
import feo.health.catalog.presentation.viewmodel.CatalogViewModelFactory

/**
 * Dagger component for the Catalog feature.
 *
 * This component brings together the dependency injection setup for the Catalog module,
 * including repository providers, use cases, and view model factories.
 */
@FeatureCatalogScope
@Component(
    modules = [CatalogModule::class, UseCaseModule::class],
    dependencies = [CatalogRepositoryProvider::class]
)
interface FeatureCatalogComponent {

    /**
     * Creates and retrieves the [CatalogViewModelFactory] instance.
     *
     * @return The factory used to instantiate Catalog view models.
     */
    fun catalogViewModelFactory(): CatalogViewModelFactory

    /**
     * Retrieves the clinic repository.
     *
     * @return The [IClinicRepository] instance.
     */
    fun clinicRepository(): IClinicRepository

    /**
     * Retrieves the disease repository.
     *
     * @return The [IDiseaseRepository] instance.
     */
    fun diseaseRepository(): IDiseaseRepository

    /**
     * Retrieves the doctor repository.
     *
     * @return The [IDoctorRepository] instance.
     */
    fun doctorRepository(): IDoctorRepository

    /**
     * Retrieves the drug repository.
     *
     * @return The [IDrugRepository] instance.
     */
    fun drugRepository(): IDrugRepository

    /**
     * Retrieves the pharmacy repository.
     *
     * @return The [IPharmacyRepository] instance.
     */
    fun pharmacyRepository(): IPharmacyRepository

    /**
     * Retrieves the search repository.
     *
     * @return The [ISearchRepository] instance.
     */
    fun searchRepository(): ISearchRepository

    /**
     * Retrieves the services repository.
     *
     * @return The [IServicesRepository] instance.
     */
    fun servicesRepository(): IServicesRepository

    /**
     * Retrieves the clinic-related use cases.
     *
     * @return The [ICatalogUseCases.Clinic] instance.
     */
    fun clinicUseCases(): ICatalogUseCases.Clinic

    /**
     * Retrieves the doctor-related use cases.
     *
     * @return The [ICatalogUseCases.Doctor] instance.
     */
    fun doctorUseCases(): ICatalogUseCases.Doctor

    /**
     * Retrieves the service-related use cases.
     *
     * @return The [ICatalogUseCases.Service] instance.
     */
    fun serviceUseCases(): ICatalogUseCases.Service

    /**
     * Retrieves the search-related use cases.
     *
     * @return The [ICatalogUseCases.Search] instance.
     */
    fun searchUseCases(): ICatalogUseCases.Search

    /**
     * Retrieves the pharmacy-related use cases.
     *
     * @return The [ICatalogUseCases.Pharmacy] instance.
     */
    fun pharmacyUseCases(): ICatalogUseCases.Pharmacy

    /**
     * Builder interface for creating instances of [FeatureCatalogComponent].
     */
    @Component.Builder
    interface Builder {

        /**
         * Binds the [CatalogRepositoryProvider] dependency into this component.
         *
         * @param catalogRepositoryProvider The provider of catalog repositories.
         * @return The [Builder] instance for method chaining.
         */
        fun bindCatalogRepositoryProvider(catalogRepositoryProvider: CatalogRepositoryProvider): Builder

        /**
         * Builds and returns the [FeatureCatalogComponent] instance.
         *
         * @return A fully constructed [FeatureCatalogComponent].
         */
        fun build(): FeatureCatalogComponent
    }
}