package feo.health.ai.data.mapper

import feo.health.ai.domain.model.request.FeatureSuggestionRequestDomain
import feo.health.ai.dto.request.SuggestionRequest
import feo.health.mapper.IMapper
import feo.health.mapper.Mapper

/**
 * Data mapping resolver between serialization [SuggestionRequest] and domain [FeatureSuggestionRequestDomain] models.
 */
@Mapper
private object SuggestionRequestMapper : IMapper<SuggestionRequest, FeatureSuggestionRequestDomain> {
    /**
     * Converts a [SuggestionRequest] serial model to its corresponding domain [FeatureSuggestionRequestDomain] entity.
     *
     * @return Resolved [FeatureSuggestionRequestDomain].
     */
    override fun SuggestionRequest.toSecond(): FeatureSuggestionRequestDomain =
        FeatureSuggestionRequestDomain(input = input)

    /**
     * Converts a [FeatureSuggestionRequestDomain] domain entity to its corresponding serial [SuggestionRequest] model.
     *
     * @return Resolved [SuggestionRequest].
     */
    override fun FeatureSuggestionRequestDomain.toFirst(): SuggestionRequest =
        SuggestionRequest(input = input)
}