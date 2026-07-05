package feo.health.catalog.data.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.data.di.FeatureCatalogScope
import feo.health.catalog.domain.use_case.util.CatalogUseCases
import feo.health.catalog.domain.use_case.util.ICatalogUseCases

/**
 * Dagger module that binds use case implementations to their respective interfaces.
 *
 * This abstract class defines binding methods for domain use cases, enabling
 * their injection into presentation or other feature components.
 */
@Module
abstract class UseCaseModule {

    /**
     * Binds the clinic use cases implementation.
     *
     * @param clinicUseCases The implementation of clinic use cases.
     * @return The clinic use cases interface [ICatalogUseCases.Clinic].
     */
    @FeatureCatalogScope
    @Binds
    abstract fun bindClinicUseCases(clinicUseCases: CatalogUseCases.Clinic): ICatalogUseCases.Clinic

    /**
     * Binds the doctor use cases implementation.
     *
     * @param clinicUseCases The implementation of doctor use cases.
     * @return The doctor use cases interface [ICatalogUseCases.Doctor].
     */
    @FeatureCatalogScope
    @Binds
    abstract fun bindDoctorUseCases(clinicUseCases: CatalogUseCases.Doctor): ICatalogUseCases.Doctor

    /**
     * Binds the service use cases implementation.
     *
     * @param clinicUseCases The implementation of service use cases.
     * @return The service use cases interface [ICatalogUseCases.Service].
     */
    @FeatureCatalogScope
    @Binds
    abstract fun bindServiceUseCases(clinicUseCases: CatalogUseCases.Service): ICatalogUseCases.Service

    /**
     * Binds the search use cases implementation.
     *
     * @param clinicUseCases The implementation of search use cases.
     * @return The search use cases interface [ICatalogUseCases.Search].
     */
    @FeatureCatalogScope
    @Binds
    abstract fun bindSearchUseCases(clinicUseCases: CatalogUseCases.Search): ICatalogUseCases.Search

    /**
     * Binds the pharmacy use cases implementation.
     *
     * @param clinicUseCases The implementation of pharmacy use cases.
     * @return The pharmacy use cases interface [ICatalogUseCases.Pharmacy].
     */
    @FeatureCatalogScope
    @Binds
    abstract fun bindPharmacyUseCases(clinicUseCases: CatalogUseCases.Pharmacy): ICatalogUseCases.Pharmacy

}