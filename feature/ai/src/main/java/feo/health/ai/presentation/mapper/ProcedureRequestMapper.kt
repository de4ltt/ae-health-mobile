package feo.health.ai.presentation.mapper

import feo.health.ai.domain.model.request.FeatureProcedureRequestDomain
import feo.health.ai.presentation.model.request.FeatureProcedureRequest
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object ProcedureRequestMapper : IMapper<FeatureProcedureRequest, FeatureProcedureRequestDomain> {
    override fun FeatureProcedureRequest.toSecond(): FeatureProcedureRequestDomain =
        FeatureProcedureRequestDomain(serviceName = serviceName)

    override fun FeatureProcedureRequestDomain.toFirst(): FeatureProcedureRequest =
        FeatureProcedureRequest(serviceName = serviceName)
}