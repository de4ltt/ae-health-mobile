package feo.health.ai.presentation.model.response

import androidx.compose.foundation.layout.fillMaxSize
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

data class FeatureProcedureResponse(
    val name: String,
    val description: String,
    val contradictions: List<String>,
    val indications: List<String>
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

        val descriptionText = HStrings.description.capitalize()
        val indicationsText = HStrings.indications.capitalize()
        val contradictionsText = HStrings.contradictions.capitalize()

        val annotated = remember {
            buildAnnotatedString {

                withStyle(boldStyle.toSpanStyle()) {
                    append("${name.capitalize()}\n\n")
                }

                if (description.isNotEmpty()) {
                    withStyle(boldStyle.toSpanStyle()) {
                        append("$descriptionText:\n")
                    }
                    appendAnnotatedXmlText(
                        text = description,
                        defaultStyle = defaultStyle,
                        linkStyle = linkStyle
                    )
                    append("\n\n")
                }

                if (indications.isNotEmpty()) {
                    withStyle(boldStyle.toSpanStyle()) {
                        append("$indicationsText:\n")
                    }
                    indications.forEach { item ->
                        append("• ")
                        appendAnnotatedXmlText(
                            text = item,
                            defaultStyle = defaultStyle,
                            linkStyle = linkStyle
                        )
                        append("\n")
                    }
                    append("\n")
                }

                if (contradictions.isNotEmpty()) {
                    withStyle(boldStyle.toSpanStyle()) {
                        append("$contradictionsText:\n")
                    }
                    contradictions.forEach { item ->
                        append("• ")
                        appendAnnotatedXmlText(
                            text = item,
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