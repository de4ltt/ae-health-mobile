package feo.health.catalog.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.di.NetworkCatalogScope
import feo.health.catalog.doctor.api.DoctorApi
import feo.health.catalog.doctor.api.IDoctorApi

/**
 * Dagger module binding the remote doctor API client implementation to its contract interface.
 */
@Module
internal abstract class DoctorModule {

    /**
     * Binds the doctor API client.
     *
     * @param doctorApi Concrete api implementation.
     * @return Bounded interface.
     */
    @NetworkCatalogScope
    @Binds
    abstract fun bindDoctorApi(doctorApi: DoctorApi): IDoctorApi
}