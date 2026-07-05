package feo.health.catalog.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.clinic.api.ClinicApi
import feo.health.catalog.clinic.api.IClinicApi
import feo.health.catalog.di.NetworkCatalogScope

/**
 * Dagger module binding the remote clinic API client implementation to its contract interface.
 */
@Module
internal abstract class ClinicModule {

    /**
     * Binds the clinic API client.
     *
     * @param clinicApi Concrete api implementation.
     * @return Bounded interface.
     */
    @NetworkCatalogScope
    @Binds
    abstract fun bindClinicApi(clinicApi: ClinicApi): IClinicApi
}