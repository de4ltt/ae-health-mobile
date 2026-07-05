package feo.health.ai.domain.use_case

import feo.health.ai.domain.model.request.FeatureSuggestionRequestDomain
import feo.health.ai.domain.repository.IAiRepository
import javax.inject.Inject

/**
 * Use case to query smart recommendations suggestion details.
 *
 * @property aiRepository AI remote services repository provider.
 */
class GetSuggestionUseCase @Inject constructor(
    private val aiRepository: IAiRepository
) {
    /**
     * Executes the suggestion recommendation query request.
     *
     * @param suggestionRequestDomain Dynamic recommendation input request details.
     * @return Resolved suggestions recommendations results.
     */
    suspend operator fun invoke(suggestionRequestDomain: FeatureSuggestionRequestDomain) =
        aiRepository.getSuggestion(suggestionRequestDomain)
}