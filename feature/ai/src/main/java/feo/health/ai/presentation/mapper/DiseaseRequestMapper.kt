package feo.health.ai.presentation.mapper

import feo.health.ai.domain.model.request.FeatureDiseaseRequestDomain
import feo.health.ai.presentation.model.request.FeatureDiseaseRequest
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object DiseaseRequestMapper : IMapper<FeatureDiseaseRequest, FeatureDiseaseRequestDomain> {
    override fun FeatureDiseaseRequest.toSecond(): FeatureDiseaseRequestDomain =
        FeatureDiseaseRequestDomain(symptoms = symptoms)

    override fun FeatureDiseaseRequestDomain.toFirst(): FeatureDiseaseRequest =
        FeatureDiseaseRequest(symptoms = symptoms)
}