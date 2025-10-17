package feo.health.catalog.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.clinic.api.ClinicApi
import feo.health.catalog.clinic.api.IClinicApi
import feo.health.catalog.di.NetworkCatalogScope

@Module
internal abstract class ClinicModule {

    @NetworkCatalogScope
    @Binds
    abstract fun bindClinicApi(clinicApi: ClinicApi): IClinicApi

}