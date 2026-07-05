package feo.health.auth.data.di

import javax.inject.Scope

/**
 * Dagger dependency injection scope limiting authentication presentation components lifetime to [FeatureAuthComponent].
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureAuthScope
