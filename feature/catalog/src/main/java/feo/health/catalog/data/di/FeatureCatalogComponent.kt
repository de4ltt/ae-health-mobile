package feo.health.catalog.data.di

import dagger.Component
import feo.health.catalog.data.di.module.CatalogModule
import feo.health.catalog.data.di.module.RepositoryModule
import feo.health.catalog.data.di.module.UseCaseModule
import feo.health.catalog.di.NetworkCatalogComponent
import feo.health.catalog.domain.repository.IClinicRepository
import feo.health.catalog.domain.repository.IDiseaseRepository
import feo.health.catalog.domain.repository.IDoctorRepository
import feo.health.catalog.domain.repository.IDrugRepository
import feo.health.catalog.domain.repository.IPharmacyRepository
import feo.health.catalog.domain.repository.ISearchRepository
import feo.health.catalog.domain.repository.IServicesRepository
import feo.health.catalog.domain.use_case.util.ICatalogUseCases
import feo.health.catalog.presentation.viewmodel.CatalogViewModelFactory

@FeatureCatalogScope
@Component(
    modules = [CatalogModule::class, RepositoryModule::class, UseCaseModule::class],
    dependencies = [NetworkCatalogComponent::class]
)
interface FeatureCatalogComponent {

    fun catalogViewModelFactory(): CatalogViewModelFactory

    fun clinicRepository(): IClinicRepository

    fun diseaseRepository(): IDiseaseRepository

    fun doctorRepository(): IDoctorRepository

    fun drugRepository(): IDrugRepository

    fun pharmacyRepository(): IPharmacyRepository

    fun searchRepository(): ISearchRepository

    fun servicesRepository(): IServicesRepository

    fun clinicUseCases(): ICatalogUseCases.Clinic

    fun doctorUseCases(): ICatalogUseCases.Doctor

    fun serviceUseCases(): ICatalogUseCases.Service

    fun searchUseCases(): ICatalogUseCases.Search

    fun pharmacyUseCases(): ICatalogUseCases.Pharmacy

    @Component.Builder
    interface Builder {

        fun bindNetworkCatalogComponent(networkCatalogComponent: NetworkCatalogComponent): Builder

        fun build(): FeatureCatalogComponent
    }
}