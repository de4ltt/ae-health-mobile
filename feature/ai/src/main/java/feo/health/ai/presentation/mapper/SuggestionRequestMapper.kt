package feo.health.ai.presentation.mapper

import feo.health.ai.domain.model.request.FeatureSuggestionRequestDomain
import feo.health.ai.presentation.model.request.FeatureSuggestionRequest
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between UI presentation model [FeatureSuggestionRequest] and domain model [FeatureSuggestionRequestDomain].
 */
@Mapper
private object SuggestionRequestMapper : IMapper<FeatureSuggestionRequest, FeatureSuggestionRequestDomain> {
    /**
     * Converts UI model [FeatureSuggestionRequest] to domain model [FeatureSuggestionRequestDomain].
     *
     * @return Resolved [FeatureSuggestionRequestDomain] entity.
     */
    override fun FeatureSuggestionRequest.toSecond(): FeatureSuggestionRequestDomain =
        FeatureSuggestionRequestDomain(input = input)

    /**
     * Converts domain model [FeatureSuggestionRequestDomain] to UI model [FeatureSuggestionRequest].
     *
     * @return Resolved [FeatureSuggestionRequest] presentation model.
     */
    override fun FeatureSuggestionRequestDomain.toFirst(): FeatureSuggestionRequest =
        FeatureSuggestionRequest(input = input)
}