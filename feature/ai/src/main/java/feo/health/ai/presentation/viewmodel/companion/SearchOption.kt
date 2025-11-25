package feo.health.ai.presentation.viewmodel.companion

import feo.health.ai.dto.request.ProcedureRequest
import feo.health.ai.presentation.model.response.ILinkingDisplay
import feo.health.ui.resource.HStrings
import java.lang.reflect.Constructor
import kotlin.reflect.KFunction

enum class SearchOption(val hintText: Int, val titleText: Int) {
    PROCEDURE(titleText = HStrings.aiProcedureRes, hintText = HStrings.aiProcedureHintRes),
    DISEASE(titleText = HStrings.aiDiseaseRes, hintText = HStrings.aiDiseaseHintRes),
    SUGGESTION(titleText = HStrings.aiSuggestionRes, hintText = HStrings.aiSuggestionHintRes)
}