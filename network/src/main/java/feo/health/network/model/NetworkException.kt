package feo.health.network.model
data class NetworkException(
    override val message: String,
    val code: String
): Exception(message)

