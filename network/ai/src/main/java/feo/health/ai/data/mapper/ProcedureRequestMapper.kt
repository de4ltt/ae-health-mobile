package feo.health.ai.data.mapper

import feo.health.ai.domain.model.request.FeatureProcedureRequestDomain
import feo.health.ai.dto.request.ProcedureRequest
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between serialization [ProcedureRequest] and domain [FeatureProcedureRequestDomain] models.
 */
@Mapper
private object ProcedureRequestMapper : IMapper<ProcedureRequest, FeatureProcedureRequestDomain> {
    /**
     * Converts a [ProcedureRequest] serial model to its corresponding domain [FeatureProcedureRequestDomain] entity.
     *
     * @return Resolved [FeatureProcedureRequestDomain].
     */
    override fun ProcedureRequest.toSecond(): FeatureProcedureRequestDomain =
        FeatureProcedureRequestDomain(serviceName = serviceName)

    /**
     * Converts a [FeatureProcedureRequestDomain] domain entity to its corresponding serial [ProcedureRequest] model.
     *
     * @return Resolved [ProcedureRequest].
     */
    override fun FeatureProcedureRequestDomain.toFirst(): ProcedureRequest =
        ProcedureRequest(serviceName = serviceName)
}