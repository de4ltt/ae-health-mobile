package feo.health.ai.data.mapper

import feo.health.ai.domain.model.response.FeatureSuggestionResponseDomain
import feo.health.ai.dto.response.SuggestionResponse
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between serialization [SuggestionResponse] and domain [FeatureSuggestionResponseDomain] models.
 */
@Mapper
private object SuggestionResponseMapper : IMapper<SuggestionResponse, FeatureSuggestionResponseDomain> {
    /**
     * Converts a [SuggestionResponse] serial model to its corresponding domain [FeatureSuggestionResponseDomain] entity.
     *
     * @return Resolved [FeatureSuggestionResponseDomain].
     */
    override fun SuggestionResponse.toSecond(): FeatureSuggestionResponseDomain =
        FeatureSuggestionResponseDomain(
            doctors = doctors,
            drugs = drugs,
            possibleDiseases = possibleDiseases,
            generalAnswer = generalAnswer
        )

    /**
     * Converts a [FeatureSuggestionResponseDomain] domain entity to its corresponding serial [SuggestionResponse] model.
     *
     * @return Resolved [SuggestionResponse].
     */
    override fun FeatureSuggestionResponseDomain.toFirst(): SuggestionResponse =
        SuggestionResponse(
            doctors = doctors,
            drugs = drugs,
            possibleDiseases = possibleDiseases,
            generalAnswer = generalAnswer
        )
}