package feo.health.catalog.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.di.NetworkCatalogScope
import feo.health.catalog.drug.api.DrugApi
import feo.health.catalog.drug.api.IDrugApi

/**
 * Dagger module binding the remote drug API client implementation to its contract interface.
 */
@Module
internal abstract class DrugModule {

    /**
     * Binds the drug API client.
     *
     * @param drugApi Concrete api implementation.
     * @return Bounded interface.
     */
    @NetworkCatalogScope
    @Binds
    abstract fun bindDrugApi(drugApi: DrugApi): IDrugApi
}