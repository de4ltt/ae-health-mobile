package feo.health.ai.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import feo.health.ai.domain.use_case.util.IAiUseCases
import feo.health.ai.presentation.mapper.FeatureDiseaseRequestToFeatureDiseaseRequestDomainMapper.toDomain
import feo.health.ai.presentation.mapper.FeatureDiseaseResponseToFeatureDiseaseResponseDomainMapper.toFeatureDiseaseResponse
import feo.health.ai.presentation.mapper.FeatureProcedureResponseToFeatureProcedureResponseDomainMapper.toFeatureProcedureResponse
import feo.health.ai.presentation.mapper.FeatureSuggestionResponseToFeatureSuggestionResponseDomainMapper.toFeatureSuggestionResponse
import feo.health.ai.presentation.model.request.FeatureDiseaseRequest
import feo.health.ai.presentation.model.request.FeatureProcedureRequest
import feo.health.ai.presentation.model.request.FeatureSuggestionRequest
import feo.health.ai.presentation.viewmodel.companion.AiEvent
import feo.health.ai.presentation.viewmodel.companion.AiState
import feo.health.ui.component.HToast.tryWithToast
import feo.health.ui.viewmodel.HViewModel
import javax.inject.Inject
import feo.health.ai.presentation.mapper.FeatureProcedureRequestToFeatureProcedureRequestDomainMapper.toDomain as toProcedureDomain
import feo.health.ai.presentation.mapper.FeatureSuggestionRequestToFeatureSuggestionRequestDomainMapper.toDomain as toSuggestionDomain

/**
 * Presentation view model class managing UI state flows and user interaction events on the AI screen.
 * Extends [HViewModel] base structure.
 *
 * @property aiUseCases Presentation use cases helper utility.
 */
class AiViewModel @Inject constructor(
    private val aiUseCases: IAiUseCases
) : HViewModel<AiState, AiEvent>(initialState = AiState.Default) {

    /**
     * Entry hook mapping incoming UI interaction events to specific handler execution tasks.
     *
     * @param event The triggered user presentation action.
     * @return Any matching return result payload wrapper.
     */
    override fun onEvent(event: AiEvent): Any = when (event) {
        AiEvent.OnBack -> onBack()
        is AiEvent.OnSearchDisease -> onSearchDisease(event.disease)
        is AiEvent.OnSearchProcedure -> onSearchProcedure(event.procedure)
        is AiEvent.OnSearchSuggestion -> onSearchSuggestion(event.suggestion)
    }

    /**
     * Triggers remote AI suggestion queries search flow updates.
     *
     * @param suggestion Suggestion query specifications.
     */
    private fun onSearchSuggestion(suggestion: FeatureSuggestionRequest) =
        viewModelScope.tryWithToast(onError = { revertScreenState() }) {
            updateScreenState(AiState.Loading)
            val result =
                aiUseCases.getSuggestionUseCase(suggestion.toSuggestionDomain())
                    .toFeatureSuggestionResponse()
            pushScreenState(AiState.Found(result))
        }

    /**
     * Triggers remote AI diagnostics query based on symptom values.
     *
     * @param disease Symptoms query specifications.
     */
    private fun onSearchDisease(disease: FeatureDiseaseRequest) =
        viewModelScope.tryWithToast(onError = { revertScreenState() }) {
            updateScreenState(AiState.Loading)
            val result = aiUseCases.getDiseaseUseCase(disease.toDomain())
                .toFeatureDiseaseResponse()
            pushScreenState(AiState.Found(result))
        }

    /**
     * Triggers detailed medical service procedure search info request.
     *
     * @param procedure Procedure query specifications.
     */
    private fun onSearchProcedure(procedure: FeatureProcedureRequest) =
        viewModelScope.tryWithToast(onError = { revertScreenState() }) {
            updateScreenState(AiState.Loading)
            val result =
                aiUseCases.getProcedureInfoUseCase(procedure.toProcedureDomain())
                    .toFeatureProcedureResponse()
            pushScreenState(AiState.Found(result))
        }
}