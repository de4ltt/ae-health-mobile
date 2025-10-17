package feo.health.catalog.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.di.NetworkCatalogScope
import feo.health.catalog.pharmacy.api.IPharmacyApi
import feo.health.catalog.pharmacy.api.PharmacyApi

@Module
internal abstract class PharmacyModule {

    @NetworkCatalogScope
    @Binds
    abstract fun bindPharmacyApi(pharmacyApi: PharmacyApi): IPharmacyApi

}