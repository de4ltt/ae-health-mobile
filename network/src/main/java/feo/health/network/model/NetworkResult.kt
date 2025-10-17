package feo.health.network.model

import feo.health.network.model.NetworkResult.Success
import io.ktor.http.HttpStatusCode

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(val exception: Throwable, val status: HttpStatusCode? = null) :
        NetworkResult<Nothing>()
}

fun <T, V> NetworkResult<T>.mapResult(mapper: (T) -> V): V =
    when (this) {
        is Success<T> -> mapper.invoke(this.data)
        is NetworkResult.Error -> throw RuntimeException(exception.message)
    }