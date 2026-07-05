package feo.health.network.di

import javax.inject.Scope

/**
 * Custom Dagger dependency injection scope used to bind singletons inside the network core component lifetime.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class NetworkModuleScope
