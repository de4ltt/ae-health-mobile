package feo.health.ui.component

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import feo.health.ui.theme.fontFamily

object HText {

    @Composable
    private operator fun invoke(
        modifier: Modifier = Modifier,
        text: String,
        color: ColorProducer? = null,
        textAlign: TextAlign = TextAlign.Start,
        fontSize: TextUnit = 15.sp,
        textDecoration: TextDecoration = TextDecoration.None,
        fontWeight: FontWeight? = FontWeight.Normal,
        maxLines: Int = Int.MAX_VALUE,
        autoSize: TextAutoSize? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        basicMarquee: Boolean = false,
        onTextLayout: ((TextLayoutResult) -> Unit)? = null
    ) = BasicText(
        onTextLayout = onTextLayout,
        modifier = modifier.let {
            if (basicMarquee)
                it.basicMarquee()
            else it
        },
        text = text,
        overflow = overflow,
        color = color,
        autoSize = autoSize,
        maxLines = maxLines,
        style = TextStyle(
            textAlign = textAlign,
            fontSize = fontSize,
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            textDecoration = textDecoration
        )
    )

    @Composable
    private operator fun invoke(
        modifier: Modifier = Modifier,
        text: AnnotatedString,
        color: ColorProducer? = null,
        textAlign: TextAlign = TextAlign.Start,
        fontSize: TextUnit = 15.sp,
        fontWeight: FontWeight? = FontWeight.Normal,
        textDecoration: TextDecoration = TextDecoration.None,
        maxLines: Int = Int.MAX_VALUE,
        autoSize: TextAutoSize? = null,
        basicMarquee: Boolean = false,
        onTextLayout: ((TextLayoutResult) -> Unit)? = null
    ) = BasicText(
        onTextLayout = onTextLayout,
        modifier = modifier.let {
            if (basicMarquee)
                it.basicMarquee()
            else it
        },
        text = text,
        color = color,
        autoSize = autoSize,
        maxLines = maxLines,
        style = TextStyle(
            textAlign = textAlign,
            fontSize = fontSize,
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            textDecoration = textDecoration
        )
    )

    @Composable
    fun DefaultWithColorProducer(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Start,
        textDecoration: TextDecoration = TextDecoration.None,
        color: ColorProducer? = null,
        fontSize: TextUnit = 15.sp,
        fontWeight: FontWeight? = FontWeight.Normal,
        onTextLayout: ((TextLayoutResult) -> Unit)? = null
    ) = invoke(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        color = color,
        textDecoration = textDecoration,
        fontSize = fontSize,
        fontWeight = fontWeight,
        onTextLayout = onTextLayout
    )

    @Composable
    fun Default(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Start,
        color: Color? = null,
        fontSize: TextUnit = 15.sp,
        textDecoration: TextDecoration = TextDecoration.None,
        fontWeight: FontWeight? = FontWeight.Normal,
        onTextLayout: ((TextLayoutResult) -> Unit)? = null
    ) = invoke(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        color = color?.let { { it } },
        fontSize = fontSize,
        textDecoration = textDecoration,
        fontWeight = fontWeight,
        onTextLayout = onTextLayout
    )

    @Composable
    fun Default(
        modifier: Modifier = Modifier,
        text: AnnotatedString,
        textAlign: TextAlign = TextAlign.Start,
        color: Color? = null,
        fontSize: TextUnit = 15.sp,
        textDecoration: TextDecoration = TextDecoration.None,
        fontWeight: FontWeight? = FontWeight.Normal,
        onTextLayout: ((TextLayoutResult) -> Unit)? = null
    ) = invoke(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        color = color?.let { { it } },
        fontSize = fontSize,
        textDecoration = textDecoration,
        fontWeight = fontWeight,
        onTextLayout = onTextLayout
    )

    @Composable
    fun SingleLineWithColorProducer(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Start,
        color: ColorProducer? = null,
        fontSize: TextUnit = 15.sp,
        textDecoration: TextDecoration = TextDecoration.None,
        fontWeight: FontWeight? = FontWeight.Normal,
        onTextLayout: ((TextLayoutResult) -> Unit)? = null
    ) = invoke(
        modifier = modifier,
        text = text,
        color = color,
        textAlign = textAlign,
        textDecoration = textDecoration,
        fontSize = fontSize,
        fontWeight = fontWeight,
        maxLines = 1,
        onTextLayout = onTextLayout
    )

    @Composable
    fun SingleLine(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Start,
        color: Color? = null,
        textDecoration: TextDecoration = TextDecoration.None,
        fontSize: TextUnit = 15.sp,
        overflow: TextOverflow = TextOverflow.Ellipsis,
        fontWeight: FontWeight? = FontWeight.Normal,
        onTextLayout: ((TextLayoutResult) -> Unit)? = null
    ) = invoke(
        modifier = modifier,
        text = text,
        color = color?.let { { it } },
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = overflow,
        fontSize = fontSize,
        fontWeight = fontWeight,
        maxLines = 1,
        onTextLayout = onTextLayout
    )

    @Composable
    fun SingleLine(
        modifier: Modifier = Modifier,
        text: AnnotatedString,
        textAlign: TextAlign = TextAlign.Start,
        color: Color? = null,
        fontSize: TextUnit = 15.sp,
        fontWeight: FontWeight? = FontWeight.Normal,
        onTextLayout: ((TextLayoutResult) -> Unit)? = null
    ) = invoke(
        modifier = modifier,
        text = text,
        color = color?.let { { it } },
        textAlign = textAlign,
        fontSize = fontSize,
        fontWeight = fontWeight,
        maxLines = 1,
        onTextLayout = onTextLayout
    )

    @Composable
    fun BasicMarquee(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Start,
        color: Color? = null,
        fontSize: TextUnit = 15.sp,
        fontWeight: FontWeight? = FontWeight.Normal,
        onTextLayout: ((TextLayoutResult) -> Unit)? = null
    ) = invoke(
        modifier = modifier,
        text = text,
        color = color?.let { { it } },
        fontSize = fontSize,
        textAlign = textAlign,
        fontWeight = fontWeight,
        maxLines = 1,
        basicMarquee = true,
        onTextLayout = onTextLayout
    )

    @Composable
    fun BasicMarqueeWithColorProducer(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign = TextAlign.Start,
        color: ColorProducer? = null,
        fontSize: TextUnit = 15.sp,
        fontWeight: FontWeight? = FontWeight.Normal,
        onTextLayout: ((TextLayoutResult) -> Unit)? = null
    ) = invoke(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize,
        textAlign = textAlign,
        fontWeight = fontWeight,
        maxLines = 1,
        basicMarquee = true,
        onTextLayout = onTextLayout
    )


    @Composable
    fun AutoSize(
        modifier: Modifier = Modifier,
        text: String,
        color: ColorProducer? = null,
        textAlign: TextAlign = TextAlign.Start,
        fontSize: TextUnit = 15.sp,
        fontWeight: FontWeight? = FontWeight.Normal,
        autoSize: TextAutoSize,
        onTextLayout: ((TextLayoutResult) -> Unit)? = null
    ) = invoke(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        maxLines = 1,
        autoSize = autoSize,
        onTextLayout = onTextLayout
    )
}