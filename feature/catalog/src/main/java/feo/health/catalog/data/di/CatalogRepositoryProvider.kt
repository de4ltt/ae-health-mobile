package feo.health.catalog.data.di

import feo.health.catalog.domain.repository.IClinicRepository
import feo.health.catalog.domain.repository.IDiseaseRepository
import feo.health.catalog.domain.repository.IDoctorRepository
import feo.health.catalog.domain.repository.IDrugRepository
import feo.health.catalog.domain.repository.IPharmacyRepository
import feo.health.catalog.domain.repository.ISearchRepository
import feo.health.catalog.domain.repository.IServicesRepository

interface CatalogRepositoryProvider {
    fun clinicRepository(): IClinicRepository
    fun diseaseRepository(): IDiseaseRepository
    fun doctorRepository(): IDoctorRepository
    fun drugRepository(): IDrugRepository
    fun pharmacyRepository(): IPharmacyRepository
    fun searchRepository(): ISearchRepository
    fun servicesRepository(): IServicesRepository
}
