package feo.health.catalog.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.di.NetworkCatalogScope
import feo.health.catalog.doctor.api.DoctorApi
import feo.health.catalog.doctor.api.IDoctorApi

@Module
internal abstract class DoctorModule {

    @NetworkCatalogScope
    @Binds
    abstract fun bindDoctorApi(doctorApi: DoctorApi): IDoctorApi

}