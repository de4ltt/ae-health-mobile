package feo.health.ai.presentation.mapper

import feo.health.ai.domain.model.request.FeatureProcedureRequestDomain
import feo.health.ai.presentation.model.request.FeatureProcedureRequest
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between UI presentation model [FeatureProcedureRequest] and domain model [FeatureProcedureRequestDomain].
 */
@Mapper
private object ProcedureRequestMapper : IMapper<FeatureProcedureRequest, FeatureProcedureRequestDomain> {
    /**
     * Converts UI model [FeatureProcedureRequest] to domain model [FeatureProcedureRequestDomain].
     *
     * @return Resolved [FeatureProcedureRequestDomain] entity.
     */
    override fun FeatureProcedureRequest.toSecond(): FeatureProcedureRequestDomain =
        FeatureProcedureRequestDomain(serviceName = serviceName)

    /**
     * Converts domain model [FeatureProcedureRequestDomain] to UI model [FeatureProcedureRequest].
     *
     * @return Resolved [FeatureProcedureRequest] presentation model.
     */
    override fun FeatureProcedureRequestDomain.toFirst(): FeatureProcedureRequest =
        FeatureProcedureRequest(serviceName = serviceName)
}