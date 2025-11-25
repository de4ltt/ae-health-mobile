package feo.health.ai.presentation.model.response

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import feo.health.ui.component.HText

interface ILinkingDisplay {
    @Composable
    fun Display(modifier: Modifier, onTextClick: (tag: String, name: String) -> Unit)

    fun AnnotatedString.Builder.appendAnnotatedXmlText(
        text: String,
        defaultStyle: TextStyle,
        linkStyle: TextStyle
    ) {
        val regex = """<(disease|doctor)\s+name=["']([^"]+)["']>(.+?)</\1>""".toRegex()
        var lastIndex = 0

        regex.findAll(text).forEach { match ->
            val start = match.range.first
            append(text.substring(lastIndex, start))

            val tag = match.groupValues[1]
            val name = match.groupValues[2]
            val inner = match.groupValues[3]

            pushStringAnnotation(tag = tag, annotation = name)
            withStyle(linkStyle.toSpanStyle()) { append(inner) }
            pop()

            lastIndex = match.range.last + 1
        }

        if (lastIndex < text.length) {
            append(text.substring(lastIndex))
        }
    }

    @Composable
    fun DisplayText(
        modifier: Modifier = Modifier,
        annotated: AnnotatedString,
        onClick: (tag: String, title: String) -> Unit
    ) {
        var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

        HText.Default(
            textAlign = TextAlign.Justify,
            modifier = modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures { offsetPosition ->
                        textLayoutResult?.let { layout ->
                            val position = layout.getOffsetForPosition(offsetPosition)
                            val end = minOf(position + 1, annotated.length)
                            annotated.getStringAnnotations(start = position, end = end)
                                .firstOrNull()
                                ?.let { ann ->
                                    onClick(ann.tag, ann.item)
                                    Log.d("HELLO", "$ann")
                                }
                        }
                    }
                },
            text = annotated,
            onTextLayout = { textLayoutResult = it }
        )
    }
}