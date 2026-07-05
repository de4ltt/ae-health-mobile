package feo.health.catalog.di

import javax.inject.Scope

/**
 * Dagger dependency injection scope limiting catalog API/Repository singletons to the lifetime of [NetworkCatalogComponent].
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkCatalogScope
