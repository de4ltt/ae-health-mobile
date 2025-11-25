package feo.health.catalog.data.di.module

import dagger.Module
import dagger.Provides
import feo.health.catalog.data.di.FeatureCatalogScope
import feo.health.catalog.domain.use_case.doctor.GetDoctorsUseCase
import feo.health.catalog.domain.use_case.search.SearchUseCase
import feo.health.catalog.domain.use_case.util.ICatalogUseCases
import feo.health.catalog.presentation.viewmodel.CatalogViewModelFactory

@Module
internal class CatalogModule {

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