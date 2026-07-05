package feo.health.catalog.data.di

import javax.inject.Scope

/**
 * Dagger scope annotation for the catalog feature.
 *
 * This scope is used to bind dependencies that should live for the duration of the
 * catalog feature flow/component lifetime.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureCatalogScope
