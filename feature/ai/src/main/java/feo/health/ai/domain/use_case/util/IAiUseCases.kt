package feo.health.ai.domain.use_case.util

import feo.health.ai.domain.use_case.GetDiseaseUseCase
import feo.health.ai.domain.use_case.GetProcedureInfoUseCase
import feo.health.ai.domain.use_case.GetSuggestionUseCase

/**
 * Interface contract representing the AI presentation use cases utility container.
 */
interface IAiUseCases {
    /**
     * Diagnostic analysis helper workflow reference.
     */
    val getDiseaseUseCase: GetDiseaseUseCase

    /**
     * Smart suggestion recommendations helper workflow reference.
     */
    val getSuggestionUseCase: GetSuggestionUseCase

    /**
     * Service procedure details helper workflow reference.
     */
    val getProcedureInfoUseCase: GetProcedureInfoUseCase
}