package feo.health.ai.presentation.mapper

import feo.health.ai.domain.model.response.FeatureSuggestionResponseDomain
import feo.health.ai.presentation.model.response.FeatureSuggestionResponse
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between UI presentation model [FeatureSuggestionResponse] and domain model [FeatureSuggestionResponseDomain].
 */
@Mapper
private object SuggestionResponseMapper : IMapper<FeatureSuggestionResponse, FeatureSuggestionResponseDomain> {
    /**
     * Converts UI model [FeatureSuggestionResponse] to domain model [FeatureSuggestionResponseDomain].
     *
     * @return Resolved [FeatureSuggestionResponseDomain] entity.
     */
    override fun FeatureSuggestionResponse.toSecond(): FeatureSuggestionResponseDomain =
        FeatureSuggestionResponseDomain(
            doctors = doctors,
            drugs = drugs,
            possibleDiseases = possibleDiseases,
            generalAnswer = generalAnswer
        )

    /**
     * Converts domain model [FeatureSuggestionResponseDomain] to UI model [FeatureSuggestionResponse].
     *
     * @return Resolved [FeatureSuggestionResponse] presentation model.
     */
    override fun FeatureSuggestionResponseDomain.toFirst(): FeatureSuggestionResponse =
        FeatureSuggestionResponse(
            doctors = doctors,
            drugs = drugs,
            possibleDiseases = possibleDiseases,
            generalAnswer = generalAnswer
        )
}