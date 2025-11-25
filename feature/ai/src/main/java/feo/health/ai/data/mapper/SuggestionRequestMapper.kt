package feo.health.ai.data.mapper

import feo.health.ai.domain.model.request.FeatureSuggestionRequestDomain
import feo.health.ai.dto.request.SuggestionRequest
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object SuggestionRequestMapper : IMapper<SuggestionRequest, FeatureSuggestionRequestDomain> {
    override fun SuggestionRequest.toSecond(): FeatureSuggestionRequestDomain =
        FeatureSuggestionRequestDomain(input = input)

    override fun FeatureSuggestionRequestDomain.toFirst(): SuggestionRequest =
        SuggestionRequest(input = input)
}