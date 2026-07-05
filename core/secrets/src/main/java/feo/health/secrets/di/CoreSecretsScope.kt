package feo.health.secrets.di

import javax.inject.Scope

/**
 * Custom Dagger scope used to bound instances within the Core Secrets component context.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class CoreSecretsScope
