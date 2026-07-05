package feo.health.ai.presentation.viewmodel.companion

import feo.health.ai.presentation.model.response.ILinkingDisplay

/**
 * Sealed representation of potential UI rendering states for the AI Diagnostic Screen.
 */
sealed class AiState {

    /**
     * Initial resting state waiting for user inputs.
     */
    data object Default : AiState()

    /**
     * Processing state rendering a loader while network requests compile.
     */
    data object Loading : AiState()

    /**
     * Fallback state rendering an empty placeholder if no matches returned.
     */
    data object NothingFound : AiState()

    /**
     * Completed state containing resolved rich text diagnostic answers content.
     *
     * @property info Presentational interface matching resolved search parameters details.
     */
    data class Found(val info: ILinkingDisplay): AiState()
}