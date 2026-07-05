package feo.health.network.model

import io.ktor.http.HttpStatusCode

/**
 * Custom network exception wrapped when receiving raw HTTP error status codes from remote server queries.
 *
 * @property message Error details representation.
 * @property status HTTP status code object representation.
 * @property cause Original throwing exception.
 */
data class HttpException(
    override val message: String,
    val status: HttpStatusCode? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)