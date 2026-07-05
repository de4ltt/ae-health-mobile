package feo.health.ui.dispatcher


/**
 * Dagger qualifier annotation used to bind and identify the CPU-bound default [CoroutineDispatcher].
 */
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultDispatcher
