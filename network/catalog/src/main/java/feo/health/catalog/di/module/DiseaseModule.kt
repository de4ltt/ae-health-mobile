package feo.health.catalog.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.di.NetworkCatalogScope
import feo.health.catalog.disease.api.DiseaseApi
import feo.health.catalog.disease.api.IDiseaseApi

/**
 * Dagger module binding the remote disease API client implementation to its contract interface.
 */
@Module
internal abstract class DiseaseModule {

    /**
     * Binds the disease API client.
     *
     * @param diseaseApi Concrete api implementation.
     * @return Bounded interface.
     */
    @NetworkCatalogScope
    @Binds
    abstract fun bindDiseaseApi(diseaseApi: DiseaseApi): IDiseaseApi
}