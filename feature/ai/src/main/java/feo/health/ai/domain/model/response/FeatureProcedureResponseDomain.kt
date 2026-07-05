package feo.health.ai.domain.model.response

/**
 * Domain response entity containing detailed properties about a medical service procedure.
 *
 * @property name Procedure name.
 * @property description Detailed description text.
 * @property contradictions List of medical contraindications/warnings.
 * @property indications List of clinical indications.
 */
data class FeatureProcedureResponseDomain(
    val name: String,
    val description: String,
    val contradictions: List<String>,
    val indications: List<String>
)
