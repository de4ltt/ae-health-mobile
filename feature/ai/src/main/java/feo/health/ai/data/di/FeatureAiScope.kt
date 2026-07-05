package feo.health.ai.data.di

import javax.inject.Scope

/**
 * Dagger dependency injection scope limiting AI repository/usecases singletons to the lifetime of [FeatureAiComponent].
 */
@Retention(AnnotationRetention.RUNTIME)
@Scope
annotation class FeatureAiScope()
