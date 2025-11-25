package feo.health.ai.data.mapper

import feo.health.ai.domain.model.response.FeatureSuggestionResponseDomain
import feo.health.ai.dto.response.SuggestionResponse
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object SuggestionResponseMapper : IMapper<SuggestionResponse, FeatureSuggestionResponseDomain> {
    override fun SuggestionResponse.toSecond(): FeatureSuggestionResponseDomain =
        FeatureSuggestionResponseDomain(
            doctors = doctors,
            drugs = drugs,
            possibleDiseases = possibleDiseases,
            generalAnswer = generalAnswer
        )

    override fun FeatureSuggestionResponseDomain.toFirst(): SuggestionResponse =
        SuggestionResponse(
            doctors = doctors,
            drugs = drugs,
            possibleDiseases = possibleDiseases,
            generalAnswer = generalAnswer
        )
}