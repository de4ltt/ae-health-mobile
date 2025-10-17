package feo.health.catalog.data.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.data.di.FeatureCatalogScope
import feo.health.catalog.data.repository.ClinicRepository
import feo.health.catalog.data.repository.DiseaseRepository
import feo.health.catalog.data.repository.DoctorRepository
import feo.health.catalog.data.repository.DrugRepository
import feo.health.catalog.data.repository.PharmacyRepository
import feo.health.catalog.data.repository.SearchRepository
import feo.health.catalog.data.repository.ServicesRepository
import feo.health.catalog.domain.repository.IClinicRepository
import feo.health.catalog.domain.repository.IDiseaseRepository
import feo.health.catalog.domain.repository.IDoctorRepository
import feo.health.catalog.domain.repository.IDrugRepository
import feo.health.catalog.domain.repository.IPharmacyRepository
import feo.health.catalog.domain.repository.ISearchRepository
import feo.health.catalog.domain.repository.IServicesRepository

@Module
internal abstract class RepositoryModule {

    @FeatureCatalogScope
    @Binds
    abstract fun bindClinicRepository(clinicRepository: ClinicRepository): IClinicRepository

    @FeatureCatalogScope
    @Binds
    abstract fun bindDiseaseRepository(diseaseRepository: DiseaseRepository): IDiseaseRepository

    @FeatureCatalogScope
    @Binds
    abstract fun bindDoctorRepository(doctorRepository: DoctorRepository): IDoctorRepository

    @FeatureCatalogScope
    @Binds
    abstract fun bindDrugRepository(drugRepository: DrugRepository): IDrugRepository

    @FeatureCatalogScope
    @Binds
    abstract fun bindPharmacyRepository(pharmacyRepository: PharmacyRepository): IPharmacyRepository

    @FeatureCatalogScope
    @Binds
    abstract fun bindSearchRepository(searchRepository: SearchRepository): ISearchRepository

    @FeatureCatalogScope
    @Binds
    abstract fun bindServicesRepository(servicesRepository: ServicesRepository): IServicesRepository

}