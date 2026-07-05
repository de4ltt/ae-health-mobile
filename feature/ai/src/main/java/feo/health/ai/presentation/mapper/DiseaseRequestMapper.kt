package feo.health.ai.presentation.mapper

import feo.health.ai.domain.model.request.FeatureDiseaseRequestDomain
import feo.health.ai.presentation.model.request.FeatureDiseaseRequest
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between UI presentation model [FeatureDiseaseRequest] and domain model [FeatureDiseaseRequestDomain].
 */
@Mapper
private object DiseaseRequestMapper : IMapper<FeatureDiseaseRequest, FeatureDiseaseRequestDomain> {
    /**
     * Converts UI model [FeatureDiseaseRequest] to domain model [FeatureDiseaseRequestDomain].
     *
     * @return Resolved [FeatureDiseaseRequestDomain] entity.
     */
    override fun FeatureDiseaseRequest.toSecond(): FeatureDiseaseRequestDomain =
        FeatureDiseaseRequestDomain(symptoms = symptoms)

    /**
     * Converts domain model [FeatureDiseaseRequestDomain] to UI model [FeatureDiseaseRequest].
     *
     * @return Resolved [FeatureDiseaseRequest] presentation model.
     */
    override fun FeatureDiseaseRequestDomain.toFirst(): FeatureDiseaseRequest =
        FeatureDiseaseRequest(symptoms = symptoms)
}