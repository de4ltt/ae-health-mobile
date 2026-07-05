package feo.health.ai.presentation.mapper

import feo.health.ai.domain.model.response.FeatureProcedureResponseDomain
import feo.health.ai.presentation.model.response.FeatureProcedureResponse
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between UI presentation model [FeatureProcedureResponse] and domain model [FeatureProcedureResponseDomain].
 */
@Mapper
private object ProcedureResponseMapper : IMapper<FeatureProcedureResponse, FeatureProcedureResponseDomain> {
    /**
     * Converts UI model [FeatureProcedureResponse] to domain model [FeatureProcedureResponseDomain].
     *
     * @return Resolved [FeatureProcedureResponseDomain] entity.
     */
    override fun FeatureProcedureResponse.toSecond(): FeatureProcedureResponseDomain =
        FeatureProcedureResponseDomain(
            name = name,
            description = description,
            contradictions = contradictions,
            indications = indications
        )

    /**
     * Converts domain model [FeatureProcedureResponseDomain] to UI model [FeatureProcedureResponse].
     *
     * @return Resolved [FeatureProcedureResponse] presentation model.
     */
    override fun FeatureProcedureResponseDomain.toFirst(): FeatureProcedureResponse =
        FeatureProcedureResponse(
            name = name,
            description = description,
            contradictions = contradictions,
            indications = indications
        )
}