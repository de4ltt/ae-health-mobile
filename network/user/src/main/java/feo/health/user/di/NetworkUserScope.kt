package feo.health.user.di

import javax.inject.Scope

/**
 * Dagger dependency injection scope limiting user API/Repository singletons to the lifetime of [NetworkUserComponent].
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkUserScope
