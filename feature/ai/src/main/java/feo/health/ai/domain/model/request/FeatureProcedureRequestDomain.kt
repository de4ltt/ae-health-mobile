package feo.health.ai.domain.model.request

/**
 * Domain request entity querying detailed medical procedurals information.
 *
 * @property serviceName Name of the medical service procedure.
 */
data class FeatureProcedureRequestDomain(
    val serviceName: String
)
