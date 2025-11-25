package feo.health.ai.presentation.mapper

import feo.health.ai.domain.model.response.FeatureProcedureResponseDomain
import feo.health.ai.presentation.model.response.FeatureProcedureResponse
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object ProcedureResponseMapper : IMapper<FeatureProcedureResponse, FeatureProcedureResponseDomain> {
    override fun FeatureProcedureResponse.toSecond(): FeatureProcedureResponseDomain =
        FeatureProcedureResponseDomain(
            name = name,
            description = description,
            contradictions = contradictions,
            indications = indications
        )

    override fun FeatureProcedureResponseDomain.toFirst(): FeatureProcedureResponse =
        FeatureProcedureResponse(
            name = name,
            description = description,
            contradictions = contradictions,
            indications = indications
        )
}