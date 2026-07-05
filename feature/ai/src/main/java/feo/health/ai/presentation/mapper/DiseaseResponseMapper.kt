package feo.health.ai.presentation.mapper

import feo.health.ai.domain.model.response.FeatureDiseaseResponseDomain
import feo.health.ai.presentation.model.response.FeatureDiseaseResponse
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between UI presentation model [FeatureDiseaseResponse] and domain model [FeatureDiseaseResponseDomain].
 */
@Mapper
private object DiseaseResponseMapper : IMapper<FeatureDiseaseResponse, FeatureDiseaseResponseDomain> {
    /**
     * Converts UI model [FeatureDiseaseResponse] to domain model [FeatureDiseaseResponseDomain].
     *
     * @return Resolved [FeatureDiseaseResponseDomain] entity.
     */
    override fun FeatureDiseaseResponse.toSecond(): FeatureDiseaseResponseDomain =
        FeatureDiseaseResponseDomain(
            possibleDiseases = possibleDiseases,
            doctors = doctors,
            generalResponse = generalResponse
        )

    /**
     * Converts domain model [FeatureDiseaseResponseDomain] to UI model [FeatureDiseaseResponse].
     *
     * @return Resolved [FeatureDiseaseResponse] presentation model.
     */
    override fun FeatureDiseaseResponseDomain.toFirst(): FeatureDiseaseResponse =
        FeatureDiseaseResponse(
            possibleDiseases = possibleDiseases,
            doctors = doctors,
            generalResponse = generalResponse
        )
}