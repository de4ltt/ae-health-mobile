package feo.health.ai.data.mapper

import feo.health.ai.domain.model.request.FeatureDiseaseRequestDomain
import feo.health.ai.domain.model.response.FeatureDiseaseResponseDomain
import feo.health.ai.dto.request.DiseaseRequest
import feo.health.ai.dto.response.DiseaseResponse
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object DiseaseResponseMapper : IMapper<DiseaseResponse, FeatureDiseaseResponseDomain> {
    override fun DiseaseResponse.toSecond(): FeatureDiseaseResponseDomain =
        FeatureDiseaseResponseDomain(
            possibleDiseases = possibleDiseases,
            doctors = doctors,
            generalResponse = generalResponse
        )

    override fun FeatureDiseaseResponseDomain.toFirst(): DiseaseResponse =
        DiseaseResponse(
            possibleDiseases = possibleDiseases,
            doctors = doctors,
            generalResponse = generalResponse
        )
}