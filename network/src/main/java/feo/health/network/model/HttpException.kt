package feo.health.network.model

import io.ktor.http.HttpStatusCode

data class HttpException(
    override val message: String,
    val status: HttpStatusCode? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)