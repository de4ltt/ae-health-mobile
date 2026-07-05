package feo.health.ai.domain.use_case

import feo.health.ai.domain.repository.IAiRepository
import feo.health.ai.domain.model.request.FeatureSuggestionRequestDomain
import javax.inject.Inject

class GetSuggestionUseCase @Inject constructor(
    private val aiRepository: IAiRepository
) {
    suspend operator fun invoke(suggestionRequestDomain: FeatureSuggestionRequestDomain) =
        aiRepository.getSuggestion(suggestionRequestDomain)
}