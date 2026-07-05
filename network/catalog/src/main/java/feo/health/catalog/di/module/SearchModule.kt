package feo.health.catalog.di.module

import dagger.Binds
import dagger.Module
import feo.health.catalog.di.NetworkCatalogScope
import feo.health.catalog.search.api.ISearchApi
import feo.health.catalog.search.api.SearchApi

/**
 * Dagger module binding the remote search API client implementation to its contract interface.
 */
@Module
internal abstract class SearchModule {

    /**
     * Binds the search API client.
     *
     * @param searchApi Concrete api implementation.
     * @return Bounded interface.
     */
    @NetworkCatalogScope
    @Binds
    abstract fun bindSearchApi(searchApi: SearchApi): ISearchApi
}