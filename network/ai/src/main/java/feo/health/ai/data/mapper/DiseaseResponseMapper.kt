package feo.health.ai.data.mapper

import feo.health.ai.domain.model.response.FeatureDiseaseResponseDomain
import feo.health.ai.dto.response.DiseaseResponse
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between serialization [DiseaseResponse] and domain [FeatureDiseaseResponseDomain] models.
 */
@Mapper
private object DiseaseResponseMapper : IMapper<DiseaseResponse, FeatureDiseaseResponseDomain> {
    /**
     * Converts a [DiseaseResponse] serial model to its corresponding domain [FeatureDiseaseResponseDomain] entity.
     *
     * @return Resolved [FeatureDiseaseResponseDomain].
     */
    override fun DiseaseResponse.toSecond(): FeatureDiseaseResponseDomain =
        FeatureDiseaseResponseDomain(
            possibleDiseases = possibleDiseases,
            doctors = doctors,
            generalResponse = generalResponse
        )

    /**
     * Converts a [FeatureDiseaseResponseDomain] domain entity to its corresponding serial [DiseaseResponse] model.
     *
     * @return Resolved [DiseaseResponse].
     */
    override fun FeatureDiseaseResponseDomain.toFirst(): DiseaseResponse =
        DiseaseResponse(
            possibleDiseases = possibleDiseases,
            doctors = doctors,
            generalResponse = generalResponse
        )
}