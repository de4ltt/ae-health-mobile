package feo.health.catalog.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.di.NetworkCatalogScope
import feo.health.catalog.search.api.ISearchApi
import feo.health.catalog.search.api.SearchApi

@Module
internal abstract class SearchModule {

    @NetworkCatalogScope
    @Binds
    abstract fun bindSearchApi(searchApi: SearchApi): ISearchApi

}