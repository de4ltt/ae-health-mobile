package feo.health.catalog.data.di.module

import dagger.Module
import dagger.Provides
import feo.health.catalog.data.di.FeatureCatalogScope
import feo.health.catalog.domain.use_case.util.ICatalogUseCases
import feo.health.catalog.presentation.viewmodel.CatalogViewModelFactory

/**
 * Dagger module that provides catalog-related presentation dependencies.
 *
 * This module is responsible for providing factories and other UI/presentation-related
 * dependencies required by the Catalog feature.
 */
@Module
internal class CatalogModule {

    /**
     * Provides the [CatalogViewModelFactory] instance configured with the necessary use cases.
     *
     * @param clinicUseCases Use cases related to clinic operations.
     * @param doctorUseCases Use cases related to doctor operations.
     * @param serviceUseCases Use cases related to service operations.
     * @param searchUseCases Use cases related to search operations.
     * @param pharmacyUseCases Use cases related to pharmacy operations.
     * @return A configured [CatalogViewModelFactory].
     */
    @FeatureCatalogScope
    @Provides
    fun provideSearchViewModelFactory(
        clinicUseCases: ICatalogUseCases.Clinic,
        doctorUseCases: ICatalogUseCases.Doctor,
        serviceUseCases: ICatalogUseCases.Service,
        searchUseCases: ICatalogUseCases.Search,
        pharmacyUseCases: ICatalogUseCases.Pharmacy
    ): CatalogViewModelFactory = CatalogViewModelFactory(
        clinicUseCases = clinicUseCases,
        doctorUseCases = doctorUseCases,
        serviceUseCases = serviceUseCases,
        searchUseCases = searchUseCases,
        pharmacyUseCases = pharmacyUseCases
    )

}