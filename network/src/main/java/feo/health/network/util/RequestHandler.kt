package feo.health.network.util

import feo.health.network.model.HttpException
import feo.health.network.model.NetworkResult
import org.slf4j.LoggerFactory
import kotlin.coroutines.cancellation.CancellationException

object RequestHandler {
    suspend fun <T> handle(block: suspend () -> T): NetworkResult<T> = try {
        val data = block()
        println(data)
        NetworkResult.Success(data)
    } catch (e: CancellationException) {
        throw e
    } catch (e: HttpException) {
        throw Exception("${e.status} + ${e.message}")
    } catch (e: Exception) {
        throw Exception("${e.message}")
    }
}

