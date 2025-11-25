package feo.health.ai.domain.use_case.util

import feo.health.ai.domain.use_case.GetDiseaseUseCase
import feo.health.ai.domain.use_case.GetProcedureInfoUseCase
import feo.health.ai.domain.use_case.GetSuggestionUseCase
import javax.inject.Inject

data class AiUseCases @Inject constructor(
    override val getDiseaseUseCase: GetDiseaseUseCase,
    override val getSuggestionUseCase: GetSuggestionUseCase,
    override val getProcedureInfoUseCase: GetProcedureInfoUseCase
): IAiUseCases
