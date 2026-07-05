package feo.health.network.util

import feo.health.network.model.HttpException
import feo.health.network.model.NetworkResult
import kotlin.coroutines.cancellation.CancellationException

/**
 * Utility executing suspending network blocks inside try-catch scopes to wrap results or failures.
 */
object RequestHandler {
    /**
     * Executes the suspending request block, catching exceptions to wrap them inside a [NetworkResult].
     *
     * @param T Result data type.
     * @param block The network request suspending operation.
     * @return [NetworkResult] wrapping successful outcome or failure details.
     * @throws CancellationException if the coroutine gets cancelled.
     */
    suspend fun <T> handle(block: suspend () -> T): NetworkResult<T> = try {
        val data = block()
        println(data)
        NetworkResult.Success(data)
    } catch (e: CancellationException) {
        throw e
    } catch (e: HttpException) {
        NetworkResult.Error(e, e.status)
    } catch (e: Exception) {
        NetworkResult.Error(e)
    }
}

