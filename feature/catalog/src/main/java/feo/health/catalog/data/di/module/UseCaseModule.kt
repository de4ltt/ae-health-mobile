package feo.health.catalog.data.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.data.di.FeatureCatalogScope
import feo.health.catalog.domain.use_case.util.CatalogUseCases
import feo.health.catalog.domain.use_case.util.ICatalogUseCases

@Module
abstract class UseCaseModule {

    @FeatureCatalogScope
    @Binds
    abstract fun bindClinicUseCases(clinicUseCases: CatalogUseCases.Clinic): ICatalogUseCases.Clinic

    @FeatureCatalogScope
    @Binds
    abstract fun bindDoctorUseCases(clinicUseCases: CatalogUseCases.Doctor): ICatalogUseCases.Doctor

    @FeatureCatalogScope
    @Binds
    abstract fun bindServiceUseCases(clinicUseCases: CatalogUseCases.Service): ICatalogUseCases.Service

    @FeatureCatalogScope
    @Binds
    abstract fun bindSearchUseCases(clinicUseCases: CatalogUseCases.Search): ICatalogUseCases.Search

    @FeatureCatalogScope
    @Binds
    abstract fun bindPharmacyUseCases(clinicUseCases: CatalogUseCases.Pharmacy): ICatalogUseCases.Pharmacy

}