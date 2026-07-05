package feo.health.auth.di

import javax.inject.Scope

/**
 * Dagger dependency injection scope limiting authentication API/Repository singletons to the lifetime of [NetworkAuthComponent].
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkAuthScope
