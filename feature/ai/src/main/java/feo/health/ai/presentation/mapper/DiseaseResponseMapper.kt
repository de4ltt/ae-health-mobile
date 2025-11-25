package feo.health.ai.presentation.mapper

import feo.health.ai.domain.model.response.FeatureDiseaseResponseDomain
import feo.health.ai.presentation.model.response.FeatureDiseaseResponse
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object DiseaseResponseMapper : IMapper<FeatureDiseaseResponse, FeatureDiseaseResponseDomain> {
    override fun FeatureDiseaseResponse.toSecond(): FeatureDiseaseResponseDomain =
        FeatureDiseaseResponseDomain(
            possibleDiseases = possibleDiseases,
            doctors = doctors,
            generalResponse = generalResponse
        )

    override fun FeatureDiseaseResponseDomain.toFirst(): FeatureDiseaseResponse =
        FeatureDiseaseResponse(
            possibleDiseases = possibleDiseases,
            doctors = doctors,
            generalResponse = generalResponse
        )
}