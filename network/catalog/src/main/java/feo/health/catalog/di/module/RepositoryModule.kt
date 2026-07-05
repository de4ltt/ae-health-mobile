package feo.health.catalog.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.data.repository.ClinicRepository
import feo.health.catalog.data.repository.DiseaseRepository
import feo.health.catalog.data.repository.DoctorRepository
import feo.health.catalog.data.repository.DrugRepository
import feo.health.catalog.data.repository.PharmacyRepository
import feo.health.catalog.data.repository.SearchRepository
import feo.health.catalog.data.repository.ServicesRepository
import feo.health.catalog.di.NetworkCatalogScope
import feo.health.catalog.domain.repository.IClinicRepository
import feo.health.catalog.domain.repository.IDiseaseRepository
import feo.health.catalog.domain.repository.IDoctorRepository
import feo.health.catalog.domain.repository.IDrugRepository
import feo.health.catalog.domain.repository.IPharmacyRepository
import feo.health.catalog.domain.repository.ISearchRepository
import feo.health.catalog.domain.repository.IServicesRepository

/**
 * Dagger module binding concrete catalog repository implementations to their domain contract interfaces.
 */
@Module
internal abstract class RepositoryModule {

    /**
     * Binds the clinic database repository.
     *
     * @param clinicRepository Concrete repository implementation.
     * @return Bounded interface.
     */
    @NetworkCatalogScope
    @Binds
    abstract fun bindClinicRepository(clinicRepository: ClinicRepository): IClinicRepository

    /**
     * Binds the disease catalog repository.
     *
     * @param diseaseRepository Concrete repository implementation.
     * @return Bounded interface.
     */
    @NetworkCatalogScope
    @Binds
    abstract fun bindDiseaseRepository(diseaseRepository: DiseaseRepository): IDiseaseRepository

    /**
     * Binds the doctor specialized repository.
     *
     * @param doctorRepository Concrete repository implementation.
     * @return Bounded interface.
     */
    @NetworkCatalogScope
    @Binds
    abstract fun bindDoctorRepository(doctorRepository: DoctorRepository): IDoctorRepository

    /**
     * Binds the drug catalog repository.
     *
     * @param drugRepository Concrete repository implementation.
     * @return Bounded interface.
     */
    @NetworkCatalogScope
    @Binds
    abstract fun bindDrugRepository(drugRepository: DrugRepository): IDrugRepository

    /**
     * Binds the pharmacy directory repository.
     *
     * @param pharmacyRepository Concrete repository implementation.
     * @return Bounded interface.
     */
    @NetworkCatalogScope
    @Binds
    abstract fun bindPharmacyRepository(pharmacyRepository: PharmacyRepository): IPharmacyRepository

    /**
     * Binds the main search queries repository.
     *
     * @param searchRepository Concrete repository implementation.
     * @return Bounded interface.
     */
    @NetworkCatalogScope
    @Binds
    abstract fun bindSearchRepository(searchRepository: SearchRepository): ISearchRepository

    /**
     * Binds the medical services repository.
     *
     * @param servicesRepository Concrete repository implementation.
     * @return Bounded interface.
     */
    @NetworkCatalogScope
    @Binds
    abstract fun bindServicesRepository(servicesRepository: ServicesRepository): IServicesRepository
}