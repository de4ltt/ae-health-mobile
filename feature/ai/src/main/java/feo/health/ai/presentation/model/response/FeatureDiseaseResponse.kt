package feo.health.ai.presentation.model.response

import feo.health.ui.R

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import feo.health.ui.theme.HTheme
import feo.health.ui.theme.fontFamily
import feo.health.ui.util.capitalize

/**
 * Presentation response entity containing diagnostic results mapped by probability metrics.
 * Implements [ILinkingDisplay] interface to display clickable annotated text formats.
 *
 * @property possibleDiseases Map of disease names mapped to probability percentages values.
 * @property doctors Recommended doctor specialties associated with diagnostic matches.
 * @property generalResponse Natural language diagnostic summary output.
 */
data class FeatureDiseaseResponse(
    val possibleDiseases: Map<String, Double>,
    val doctors: List<String>,
    val generalResponse: String
) : ILinkingDisplay {

    /**
     * Renders the diagnostic response details as formatted annotated text with support for interactive clicks.
     *
     * @param modifier Composable layout adjustments modifier.
     * @param onTextClick Callback invoked when user clicks an interactive reference link tag.
     */
    @Composable
    override fun Display(modifier: Modifier, onTextClick: (tag: String, name: String) -> Unit) {
        val fontSize = 17.sp
        val defaultStyle = TextStyle(
            fontSize = fontSize,
            fontFamily = fontFamily,
            color = HTheme.colors.onBackground
        )
        val boldStyle = defaultStyle.copy(fontWeight = FontWeight.Bold)
        val linkStyle = defaultStyle.copy(
            color = HTheme.colors.secondary,
            textDecoration = TextDecoration.Underline
        )

        val possibleDiseasesText = stringResource(R.string.possible_diseases).capitalize()
        val recommendedDoctorsText = stringResource(R.string.recommended_doctors).capitalize()

        val annotated = remember(possibleDiseases, doctors, generalResponse) {
            buildAnnotatedString {
                appendAnnotatedXmlText(
                    text = generalResponse,
                    defaultStyle = defaultStyle,
                    linkStyle = linkStyle
                )
                append("\n\n")

                if (possibleDiseases.entries.isNotEmpty()) {
                    withStyle(boldStyle.toSpanStyle()) {
                        append("$possibleDiseasesText:\n")
                    }
                    possibleDiseases.entries
                        .sortedByDescending { it.value }
                        .forEach { (disease, probability) ->
                            append("• ")
                            withStyle(defaultStyle.toSpanStyle()) {
                                append(disease)
                            }
                            append(" — ")
                            withStyle(boldStyle.toSpanStyle()) {
                                append(String.format("%.1f%%", probability * 100))
                            }
                            append("\n")
                        }
                    append("\n")
                }

                if (doctors.isNotEmpty()) {
                    withStyle(boldStyle.toSpanStyle()) {
                        append("$recommendedDoctorsText:\n")
                    }
                    doctors.forEach { doctor ->
                        append("• ")
                        appendAnnotatedXmlText(
                            text = doctor,
                            defaultStyle = defaultStyle,
                            linkStyle = linkStyle
                        )
                        append("\n")
                    }
                }
            }
        }

        DisplayText(
            modifier = modifier.fillMaxSize(),
            annotated = annotated,
            onClick = onTextClick
        )
    }
}
