package feo.health.ai.data.mapper

import feo.health.ai.domain.model.response.FeatureProcedureResponseDomain
import feo.health.ai.dto.response.ProcedureResponse
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object ProcedureResponseMapper : IMapper<ProcedureResponse, FeatureProcedureResponseDomain> {
    override fun ProcedureResponse.toSecond(): FeatureProcedureResponseDomain =
        FeatureProcedureResponseDomain(
            name = name,
            description = description,
            contradictions = contradictions,
            indications = indications
        )

    override fun FeatureProcedureResponseDomain.toFirst(): ProcedureResponse =
        ProcedureResponse(
            name = name,
            description = description,
            contradictions = contradictions,
            indications = indications
        )
}