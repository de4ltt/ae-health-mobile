package feo.health.catalog.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.di.NetworkCatalogScope
import feo.health.catalog.drug.api.DrugApi
import feo.health.catalog.drug.api.IDrugApi

@Module
internal abstract class DrugModule {

    @NetworkCatalogScope
    @Binds
    abstract fun bindDrugApi(drugApi: DrugApi): IDrugApi

}