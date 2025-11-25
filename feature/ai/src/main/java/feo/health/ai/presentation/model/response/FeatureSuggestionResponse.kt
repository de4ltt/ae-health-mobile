package feo.health.ai.presentation.model.response

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import feo.health.ui.resource.HStrings
import feo.health.ui.resource.HStrings.capitalize
import feo.health.ui.theme.HTheme
import feo.health.ui.theme.fontFamily

data class FeatureSuggestionResponse(
    val doctors: List<String>,
    val drugs: List<String>,
    val possibleDiseases: Map<String, Double>,
    val generalAnswer: String
) : ILinkingDisplay {

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

        val possibleDiseasesText = HStrings.possibleDiseases.capitalize()
        val recommendedDoctorsText = HStrings.recommendedDoctors.capitalize()
        val possibleMedicationsText = HStrings.possibleMedications.capitalize()

        val annotated = remember(doctors, drugs, possibleDiseases, generalAnswer) {
            buildAnnotatedString {
                appendAnnotatedXmlText(
                    text = generalAnswer,
                    defaultStyle = defaultStyle,
                    linkStyle = linkStyle
                )
                append("\n\n")

                if (possibleDiseases.isNotEmpty()) {
                    withStyle(boldStyle.toSpanStyle()) {
                        append("$possibleDiseasesText:\n")
                    }
                    possibleDiseases.entries
                        .sortedByDescending { it.value }
                        .forEach { (disease, prob) ->
                            append("• ")
                            append(disease)
                            append(" — ")
                            withStyle(boldStyle.toSpanStyle()) {
                                append(String.format("%.1f%%", prob * 100))
                            }
                            append("\n")
                        }
                    append("\n")
                }

                if (drugs.isNotEmpty()) {
                    withStyle(boldStyle.toSpanStyle()) {
                        append("$possibleMedicationsText:\n")
                    }
                    drugs.forEach { drug ->
                        append("• ")
                        appendAnnotatedXmlText(
                            text = drug,
                            defaultStyle = defaultStyle,
                            linkStyle = linkStyle
                        )
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