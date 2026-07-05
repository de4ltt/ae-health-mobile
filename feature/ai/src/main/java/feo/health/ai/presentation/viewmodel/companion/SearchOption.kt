package feo.health.ai.presentation.viewmodel.companion

import feo.health.ai.presentation.model.response.ILinkingDisplay
import feo.health.ui.R
import java.lang.reflect.Constructor
import kotlin.reflect.KFunction

enum class SearchOption(val hintText: Int, val titleText: Int) {
    PROCEDURE(titleText = R.string.ai_procedure, hintText = R.string.ai_procedure_hint),
    DISEASE(titleText = R.string.ai_disesase, hintText = R.string.ai_disesase_hint),
    SUGGESTION(titleText = R.string.ai_suggestion, hintText = R.string.ai_suggestion_hint)
}