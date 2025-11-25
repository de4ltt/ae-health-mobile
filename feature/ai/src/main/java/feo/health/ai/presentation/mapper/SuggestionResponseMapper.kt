package feo.health.ai.presentation.mapper

import feo.health.ai.domain.model.response.FeatureSuggestionResponseDomain
import feo.health.ai.presentation.model.response.FeatureSuggestionResponse
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object SuggestionResponseMapper : IMapper<FeatureSuggestionResponse, FeatureSuggestionResponseDomain> {
    override fun FeatureSuggestionResponse.toSecond(): FeatureSuggestionResponseDomain =
        FeatureSuggestionResponseDomain(
            doctors = doctors,
            drugs = drugs,
            possibleDiseases = possibleDiseases,
            generalAnswer = generalAnswer
        )

    override fun FeatureSuggestionResponseDomain.toFirst(): FeatureSuggestionResponse =
        FeatureSuggestionResponse(
            doctors = doctors,
            drugs = drugs,
            possibleDiseases = possibleDiseases,
            generalAnswer = generalAnswer
        )
}