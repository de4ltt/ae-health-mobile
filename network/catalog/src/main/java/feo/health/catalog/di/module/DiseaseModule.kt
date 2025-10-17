package feo.health.catalog.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.di.NetworkCatalogScope
import feo.health.catalog.disease.api.DiseaseApi
import feo.health.catalog.disease.api.IDiseaseApi

@Module
internal abstract class DiseaseModule {

    @NetworkCatalogScope
    @Binds
    abstract fun bindDiseaseApi(diseaseApi: DiseaseApi): IDiseaseApi

}