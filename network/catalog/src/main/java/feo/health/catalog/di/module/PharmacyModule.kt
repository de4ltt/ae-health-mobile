package feo.health.catalog.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.di.NetworkCatalogScope
import feo.health.catalog.pharmacy.api.IPharmacyApi
import feo.health.catalog.pharmacy.api.PharmacyApi

/**
 * Dagger module binding the remote pharmacy API client implementation to its contract interface.
 */
@Module
internal abstract class PharmacyModule {

    /**
     * Binds the pharmacy API client.
     *
     * @param pharmacyApi Concrete api implementation.
     * @return Bounded interface.
     */
    @NetworkCatalogScope
    @Binds
    abstract fun bindPharmacyApi(pharmacyApi: PharmacyApi): IPharmacyApi
}