package feo.health.ai.presentation.model.response

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
 * Presentation response entity containing detailed properties about a medical service procedure.
 * Implements [ILinkingDisplay] interface to display clickable annotated text formats.
 *
 * @property name Procedure name.
 * @property description Detailed description text.
 * @property contradictions List of medical contraindications/warnings.
 * @property indications List of clinical indications.
 */
data class FeatureProcedureResponse(
    val name: String,
    val description: String,
    val contradictions: List<String>,
    val indications: List<String>
) : ILinkingDisplay {
    /**
     * Renders the procedure response details as formatted annotated text with support for interactive clicks.
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

        val descriptionText = stringResource(feo.health.ui.R.string.description).capitalize()
        val indicationsText = stringResource(feo.health.ui.R.string.indication).capitalize()
        val contradictionsText = stringResource(feo.health.ui.R.string.contradictions).capitalize()

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