package feo.health.user.components.data.di

import javax.inject.Scope

/**
 * Dagger scope annotation for the user feature module.
 *
 * Ensures that dependencies associated with this scope live as long as the containing
 * [FeatureUserComponent] instance.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureUserScope