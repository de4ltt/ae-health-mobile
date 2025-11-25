package feo.health.ai.presentation.viewmodel.companion

import feo.health.ai.presentation.model.request.FeatureDiseaseRequest
import feo.health.ai.presentation.model.request.FeatureProcedureRequest
import feo.health.ai.presentation.model.request.FeatureSuggestionRequest

sealed interface AiEvent {

    data class OnSearchDisease(val disease: FeatureDiseaseRequest): AiEvent
    data class OnSearchProcedure(val procedure: FeatureProcedureRequest): AiEvent
    data class OnSearchSuggestion(val suggestion: FeatureSuggestionRequest): AiEvent

    data object OnBack: AiEvent
}