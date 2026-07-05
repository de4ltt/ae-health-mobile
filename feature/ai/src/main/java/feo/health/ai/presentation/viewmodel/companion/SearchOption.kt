package feo.health.ai.presentation.viewmodel.companion

import feo.health.ui.R

/**
 * Enumeration mapping active diagnostic search categories to their corresponding localization resource keys.
 *
 * @property hintText Resource ID pointing to the localizable helper text bar input hint string.
 * @property titleText Resource ID pointing to the localizable toggle selector header title string.
 */
enum class SearchOption(val hintText: Int, val titleText: Int) {
    /**
     * Category workflow focused on medical procedures specifications search queries.
     */
    PROCEDURE(titleText = R.string.ai_procedure, hintText = R.string.ai_procedure_hint),

    /**
     * Category workflow focused on symptoms diagnostics queries.
     */
    DISEASE(titleText = R.string.ai_disesase, hintText = R.string.ai_disesase_hint),

    /**
     * Category workflow focused on smart recommendations search queries.
     */
    SUGGESTION(titleText = R.string.ai_suggestion, hintText = R.string.ai_suggestion_hint)
}