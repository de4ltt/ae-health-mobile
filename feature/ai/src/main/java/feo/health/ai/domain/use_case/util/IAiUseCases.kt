package feo.health.ai.domain.use_case.util

import feo.health.ai.domain.use_case.GetDiseaseUseCase
import feo.health.ai.domain.use_case.GetProcedureInfoUseCase
import feo.health.ai.domain.use_case.GetSuggestionUseCase

interface IAiUseCases {
    val getDiseaseUseCase: GetDiseaseUseCase
    val getSuggestionUseCase: GetSuggestionUseCase
    val getProcedureInfoUseCase: GetProcedureInfoUseCase
}