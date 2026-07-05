package feo.health.ui.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object AppDispatchers {
    var main: CoroutineDispatcher = Dispatchers.Main
    var io: CoroutineDispatcher = Dispatchers.IO
    var default: CoroutineDispatcher = Dispatchers.Default
    var unconfined: CoroutineDispatcher = Dispatchers.Unconfined
}
