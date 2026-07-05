package feo.health.network.model

import feo.health.network.model.NetworkResult.Success
import io.ktor.http.HttpStatusCode

/**
 * Sealed class wrapping remote network request outcome states.
 *
 * @param T The type of data content returned on success.
 */
sealed class NetworkResult<out T> {
    /**
     * Successful API query completion containing the payload data.
     *
     * @param T The type of data content.
     * @property data The response payload.
     */
    data class Success<T>(val data: T) : NetworkResult<T>()

    /**
     * Failed API query execution containing error exception properties.
     *
     * @property exception The thrown network exception.
     * @property status HTTP status code associated with the error, or `null` if network timed out.
     */
    data class Error(val exception: Throwable, val status: HttpStatusCode? = null) :
        NetworkResult<Nothing>()
}

/**
 * Maps the successful result data to another type, or throws a detailed exception on failure.
 *
 * @param T Source type.
 * @param V Destination mapped type.
 * @param mapper Mapper callback to convert data.
 * @return Mapped destination type data.
 * @throws Throwable wrapping details of the connection error if execution failed.
 */
fun <T, V> NetworkResult<T>.mapResult(mapper: (T) -> V): V =
    when (this) {
        is Success<T> -> mapper.invoke(this.data)
        is NetworkResult.Error -> throw Throwable(
            message = ("${this.status?.value ?: 503} " + " " + exception.message)
        )
    }