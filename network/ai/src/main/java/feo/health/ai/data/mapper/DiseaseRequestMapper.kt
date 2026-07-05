package feo.health.ai.data.mapper

import feo.health.ai.domain.model.request.FeatureDiseaseRequestDomain
import feo.health.ai.dto.request.DiseaseRequest
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between serialization [DiseaseRequest] and domain [FeatureDiseaseRequestDomain] models.
 */
@Mapper
private object DiseaseRequestMapper : IMapper<DiseaseRequest, FeatureDiseaseRequestDomain> {
    /**
     * Converts a [DiseaseRequest] serial model to its corresponding domain [FeatureDiseaseRequestDomain] entity.
     *
     * @return Resolved [FeatureDiseaseRequestDomain].
     */
    override fun DiseaseRequest.toSecond(): FeatureDiseaseRequestDomain =
        FeatureDiseaseRequestDomain(symptoms = symptoms)

    /**
     * Converts a [FeatureDiseaseRequestDomain] domain entity to its corresponding serial [DiseaseRequest] model.
     *
     * @return Resolved [DiseaseRequest].
     */
    override fun FeatureDiseaseRequestDomain.toFirst(): DiseaseRequest =
        DiseaseRequest(symptoms = symptoms)
}