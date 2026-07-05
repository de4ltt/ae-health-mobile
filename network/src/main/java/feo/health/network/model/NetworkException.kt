package feo.health.network.model
/**
 * Custom runtime exception wrapped when the API call throws a server/logical error containing details.
 *
 * @property message Detailed description explaining the exception reason.
 * @property code Custom application/error code returned by the server.
 */
data class NetworkException(
    override val message: String,
    val code: String
): Exception(message)

