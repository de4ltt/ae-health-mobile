package feo.health.ai.data.mapper

import feo.health.ai.domain.model.response.FeatureProcedureResponseDomain
import feo.health.ai.dto.response.ProcedureResponse
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between serialization [ProcedureResponse] and domain [FeatureProcedureResponseDomain] models.
 */
@Mapper
private object ProcedureResponseMapper : IMapper<ProcedureResponse, FeatureProcedureResponseDomain> {
    /**
     * Converts a [ProcedureResponse] serial model to its corresponding domain [FeatureProcedureResponseDomain] entity.
     *
     * @return Resolved [FeatureProcedureResponseDomain].
     */
    override fun ProcedureResponse.toSecond(): FeatureProcedureResponseDomain =
        FeatureProcedureResponseDomain(
            name = name,
            description = description,
            contradictions = contradictions,
            indications = indications
        )

    /**
     * Converts a [FeatureProcedureResponseDomain] domain entity to its corresponding serial [ProcedureResponse] model.
     *
     * @return Resolved [ProcedureResponse].
     */
    override fun FeatureProcedureResponseDomain.toFirst(): ProcedureResponse =
        ProcedureResponse(
            name = name,
            description = description,
            contradictions = contradictions,
            indications = indications
        )
}