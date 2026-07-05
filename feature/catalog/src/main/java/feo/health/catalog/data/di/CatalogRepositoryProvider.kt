package feo.health.catalog.data.di

import feo.health.catalog.domain.repository.IClinicRepository
import feo.health.catalog.domain.repository.IDiseaseRepository
import feo.health.catalog.domain.repository.IDoctorRepository
import feo.health.catalog.domain.repository.IDrugRepository
import feo.health.catalog.domain.repository.IPharmacyRepository
import feo.health.catalog.domain.repository.ISearchRepository
import feo.health.catalog.domain.repository.IServicesRepository

/**
 * Provider interface for retrieving various catalog repositories.
 *
 * This provider exposes access to the repositories dealing with clinics,
 * diseases, doctors, drugs, pharmacies, search operations, and services.
 */
interface CatalogRepositoryProvider {
    /**
     * Retrieves the repository for managing clinic-related data.
     *
     * @return The [IClinicRepository] instance.
     */
    fun clinicRepository(): IClinicRepository

    /**
     * Retrieves the repository for managing disease-related data.
     *
     * @return The [IDiseaseRepository] instance.
     */
    fun diseaseRepository(): IDiseaseRepository

    /**
     * Retrieves the repository for managing doctor-related data.
     *
     * @return The [IDoctorRepository] instance.
     */
    fun doctorRepository(): IDoctorRepository

    /**
     * Retrieves the repository for managing drug-related data.
     *
     * @return The [IDrugRepository] instance.
     */
    fun drugRepository(): IDrugRepository

    /**
     * Retrieves the repository for managing pharmacy-related data.
     *
     * @return The [IPharmacyRepository] instance.
     */
    fun pharmacyRepository(): IPharmacyRepository

    /**
     * Retrieves the repository for performing catalog search operations.
     *
     * @return The [ISearchRepository] instance.
     */
    fun searchRepository(): ISearchRepository

    /**
     * Retrieves the repository for managing service-related data.
     *
     * @return The [IServicesRepository] instance.
     */
    fun servicesRepository(): IServicesRepository
}

