package feo.health.ai.presentation.viewmodel.companion

import feo.health.ai.presentation.model.request.FeatureDiseaseRequest
import feo.health.ai.presentation.model.request.FeatureProcedureRequest
import feo.health.ai.presentation.model.request.FeatureSuggestionRequest

/**
 * Sealed interface representing UI interaction events triggered on the AI screen.
 */
sealed interface AiEvent {

    /**
     * Event triggered to query diagnostics based on symptom details.
     *
     * @property disease Symptoms query request wrapper details.
     */
    data class OnSearchDisease(val disease: FeatureDiseaseRequest): AiEvent

    /**
     * Event triggered to query medical service procedure detailed specs.
     *
     * @property procedure Procedure query request wrapper details.
     */
    data class OnSearchProcedure(val procedure: FeatureProcedureRequest): AiEvent

    /**
     * Event triggered to query smart recommendations suggestions matching raw user input text.
     *
     * @property suggestion Suggestion query request wrapper details.
     */
    data class OnSearchSuggestion(val suggestion: FeatureSuggestionRequest): AiEvent

    /**
     * Event triggered when navigation back stack action occurs.
     */
    data object OnBack: AiEvent
}