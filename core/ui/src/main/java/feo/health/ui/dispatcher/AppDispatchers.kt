package feo.health.ui.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Holder container providing coroutine dispatchers used across the application scope.
 * Allows easy swapping or overriding of dispatchers during Unit testing.
 */
object AppDispatchers {
    /**
     * Primary dispatcher for Main thread operations.
     */
    var main: CoroutineDispatcher = Dispatchers.Main

    /**
     * Dispatcher optimized for input/output and disk operations.
     */
    var io: CoroutineDispatcher = Dispatchers.IO

    /**
     * Dispatcher optimized for CPU-bound computations.
     */
    var default: CoroutineDispatcher = Dispatchers.Default

    /**
     * Unconfined execution dispatcher.
     */
    var unconfined: CoroutineDispatcher = Dispatchers.Unconfined
}
