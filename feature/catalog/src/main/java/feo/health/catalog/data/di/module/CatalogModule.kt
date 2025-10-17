package feo.health.catalog.data.di.module

import dagger.Module
import dagger.Provides
import feo.health.catalog.data.di.FeatureCatalogScope
import feo.health.catalog.domain.use_case.search.SearchUseCase
import feo.health.catalog.presentation.viewmodel.CatalogViewModelFactory

@Module
internal class CatalogModule {

    @FeatureCatalogScope
    @Provides
    fun provideSearchViewModelFactory(
        searchUseCase: SearchUseCase
    ): CatalogViewModelFactory = CatalogViewModelFactory(
        searchUseCase
    )

}