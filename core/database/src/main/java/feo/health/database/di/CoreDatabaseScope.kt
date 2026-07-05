package feo.health.database.di

import javax.inject.Scope

/**
 * Custom Dagger scope targeting database module lifetime constraints.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class CoreDatabaseScope
