package feo.health.ai.data.mapper

import feo.health.ai.domain.model.request.FeatureDiseaseRequestDomain
import feo.health.ai.dto.request.DiseaseRequest
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object DiseaseRequestMapper : IMapper<DiseaseRequest, FeatureDiseaseRequestDomain> {
    override fun DiseaseRequest.toSecond(): FeatureDiseaseRequestDomain =
        FeatureDiseaseRequestDomain(symptoms = symptoms)

    override fun FeatureDiseaseRequestDomain.toFirst(): DiseaseRequest =
        DiseaseRequest(symptoms = symptoms)
}