package feo.health.ai.presentation.model.request

/**
 * Presentation request entity querying detailed medical procedurals information.
 *
 * @property serviceName Name of the medical service procedure.
 */
data class FeatureProcedureRequest(
    val serviceName: String
)
