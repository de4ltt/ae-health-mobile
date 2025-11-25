package feo.health.ai.data.mapper

import feo.health.ai.domain.model.request.FeatureProcedureRequestDomain
import feo.health.ai.dto.request.ProcedureRequest
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object ProcedureRequestMapper : IMapper<ProcedureRequest, FeatureProcedureRequestDomain> {
    override fun ProcedureRequest.toSecond(): FeatureProcedureRequestDomain =
        FeatureProcedureRequestDomain(serviceName = serviceName)

    override fun FeatureProcedureRequestDomain.toFirst(): ProcedureRequest =
        ProcedureRequest(serviceName = serviceName)
}