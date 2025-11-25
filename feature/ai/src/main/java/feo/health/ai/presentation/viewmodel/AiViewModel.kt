package feo.health.ai.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import feo.health.ai.domain.use_case.util.IAiUseCases
import feo.health.ai.presentation.mapper.FeatureDiseaseRequestToFeatureDiseaseRequestDomainMapper.toFeatureDiseaseRequestDomain
import feo.health.ai.presentation.mapper.FeatureDiseaseResponseToFeatureDiseaseResponseDomainMapper.toFeatureDiseaseResponse
import feo.health.ai.presentation.mapper.FeatureProcedureRequestToFeatureProcedureRequestDomainMapper.toFeatureProcedureRequestDomain
import feo.health.ai.presentation.mapper.FeatureProcedureResponseToFeatureProcedureResponseDomainMapper.toFeatureProcedureResponse
import feo.health.ai.presentation.mapper.FeatureSuggestionRequestToFeatureSuggestionRequestDomainMapper.toFeatureSuggestionRequestDomain
import feo.health.ai.presentation.mapper.FeatureSuggestionResponseToFeatureSuggestionResponseDomainMapper.toFeatureSuggestionResponse
import feo.health.ai.presentation.model.request.FeatureDiseaseRequest
import feo.health.ai.presentation.model.request.FeatureProcedureRequest
import feo.health.ai.presentation.model.request.FeatureSuggestionRequest
import feo.health.ai.presentation.viewmodel.companion.AiEvent
import feo.health.ai.presentation.viewmodel.companion.AiState
import feo.health.ai.presentation.viewmodel.companion.SearchOption
import feo.health.ui.component.HToast.tryWithToast
import feo.health.ui.viewmodel.HViewModel
import javax.inject.Inject

class AiViewModel @Inject constructor(
    private val aiUseCases: IAiUseCases
) : HViewModel<AiState, AiEvent>(initialState = AiState.Default) {

    override fun onEvent(event: AiEvent): Any = when (event) {
        AiEvent.OnBack -> onBack()
        is AiEvent.OnSearchDisease -> onSearchDisease(event.disease)
        is AiEvent.OnSearchProcedure -> onSearchProcedure(event.procedure)
        is AiEvent.OnSearchSuggestion -> onSearchSuggestion(event.suggestion)
    }

    private fun onSearchSuggestion(suggestion: FeatureSuggestionRequest) =
        viewModelScope.tryWithToast(onError = { revertScreenState() }) {
            updateScreenState(AiState.Loading)
            val result =
                aiUseCases.getSuggestionUseCase(suggestion.toFeatureSuggestionRequestDomain())
                    .toFeatureSuggestionResponse()
            pushScreenState(AiState.Found(result))
        }

    private fun onSearchDisease(disease: FeatureDiseaseRequest) =
        viewModelScope.tryWithToast(onError = { revertScreenState() }) {
            updateScreenState(AiState.Loading)
            val result = aiUseCases.getDiseaseUseCase(disease.toFeatureDiseaseRequestDomain())
                .toFeatureDiseaseResponse()
            pushScreenState(AiState.Found(result))
        }

    private fun onSearchProcedure(procedure: FeatureProcedureRequest) =
        viewModelScope.tryWithToast(onError = { revertScreenState() }) {
            updateScreenState(AiState.Loading)
            val result =
                aiUseCases.getProcedureInfoUseCase(procedure.toFeatureProcedureRequestDomain())
                    .toFeatureProcedureResponse()
            pushScreenState(AiState.Found(result))
        }
}