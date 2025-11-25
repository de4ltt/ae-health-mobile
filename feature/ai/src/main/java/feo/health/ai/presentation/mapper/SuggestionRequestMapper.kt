package feo.health.ai.presentation.mapper

import feo.health.ai.domain.model.request.FeatureSuggestionRequestDomain
import feo.health.ai.presentation.model.request.FeatureSuggestionRequest
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

@Mapper
private object SuggestionRequestMapper : IMapper<FeatureSuggestionRequest, FeatureSuggestionRequestDomain> {
    override fun FeatureSuggestionRequest.toSecond(): FeatureSuggestionRequestDomain =
        FeatureSuggestionRequestDomain(input = input)

    override fun FeatureSuggestionRequestDomain.toFirst(): FeatureSuggestionRequest =
        FeatureSuggestionRequest(input = input)
}