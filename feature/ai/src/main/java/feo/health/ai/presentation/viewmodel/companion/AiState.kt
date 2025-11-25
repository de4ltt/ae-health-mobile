package feo.health.ai.presentation.viewmodel.companion

import androidx.compose.runtime.Composable
import feo.health.ai.presentation.model.response.FeatureDiseaseResponse
import feo.health.ai.presentation.model.response.FeatureProcedureResponse
import feo.health.ai.presentation.model.response.FeatureSuggestionResponse
import feo.health.ai.presentation.model.response.ILinkingDisplay
import feo.health.ui.util.ILoading

sealed class AiState {

    data object Default : AiState()
    data object Loading : AiState()
    data object NothingFound : AiState()
    data class Found(val info: ILinkingDisplay): AiState()
}