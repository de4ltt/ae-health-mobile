package feo.health.ai.di

import javax.inject.Scope

/**
 * Dagger dependency injection scope limiting AI API/Repository singletons to the lifetime of [NetworkAIComponent].
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkAIScope
