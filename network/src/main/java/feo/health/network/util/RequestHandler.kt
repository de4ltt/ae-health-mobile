package feo.health.network.util

import feo.health.network.model.HttpException
import feo.health.network.model.NetworkResult
import org.slf4j.LoggerFactory
import kotlin.coroutines.cancellation.CancellationException

object RequestHandler {

    private val logger = LoggerFactory.getLogger(RequestHandler::class.java)

    suspend fun <T> handle(block: suspend () -> T): NetworkResult<T> = try {
        NetworkResult.Success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: HttpException) {
        NetworkResult.Error(e, e.status)
    } catch (e: Exception) {
        logger.error("Network error", e)
        NetworkResult.Error(e)
    }
}

