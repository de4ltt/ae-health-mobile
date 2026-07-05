package feo.health.ui.component

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.text.BasicText
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import feo.health.ui.theme.fontFamily

/**
 * A custom text component wrapper around Jetpack Compose [BasicText] that enforces
 * the application's unified typography ([fontFamily]).
 *
 * It provides various layout and animation presets, such as marquee scrolling,
 * single-line constraints with ellipsis, and auto-sizing text.
 */
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

    /**
     * Renders standard multi-line text using a dynamic [ColorProducer] to optimize
     * color recalculations and prevent unnecessary recompositions.
     *
     * @param modifier The [Modifier] to apply to the text layout.
     * @param text The plain text string to render.
     * @param textAlign Alignment of the text within the container. Defaults to [TextAlign.Start].
     * @param textDecoration Decorations (e.g. underline/strike-through) to apply. Defaults to [TextDecoration.None].
     * @param color The [ColorProducer] that provides the text color.
     * @param fontSize Size of the text font. Defaults to 15.sp.
     * @param fontWeight Thickness of the text font. Defaults to [FontWeight.Normal].
     * @param onTextLayout Callback invoked when the text layout is computed.
     */
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

    /**
     * Renders standard multi-line text.
     *
     * @param modifier The [Modifier] to apply to the text layout.
     * @param text The plain text string to render.
     * @param textAlign Alignment of the text within the container. Defaults to [TextAlign.Start].
     * @param color Color of the text. If null, standard styling is used.
     * @param fontSize Size of the text font. Defaults to 15.sp.
     * @param textDecoration Decorations (e.g. underline/strike-through) to apply. Defaults to [TextDecoration.None].
     * @param fontWeight Thickness of the text font. Defaults to [FontWeight.Normal].
     * @param onTextLayout Callback invoked when the text layout is computed.
     */
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

    /**
     * Renders standard multi-line text with annotated string styles.
     *
     * @param modifier The [Modifier] to apply to the text layout.
     * @param text The [AnnotatedString] containing styled text runs.
     * @param textAlign Alignment of the text within the container. Defaults to [TextAlign.Start].
     * @param color Color of the text. If null, standard styling is used.
     * @param fontSize Size of the text font. Defaults to 15.sp.
     * @param textDecoration Decorations (e.g. underline/strike-through) to apply. Defaults to [TextDecoration.None].
     * @param fontWeight Thickness of the text font. Defaults to [FontWeight.Normal].
     * @param onTextLayout Callback invoked when the text layout is computed.
     */
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

    /**
     * Renders text constrained to a single line, utilizing a dynamic [ColorProducer].
     *
     * @param modifier The [Modifier] to apply to the text layout.
     * @param text The plain text string to render.
     * @param textAlign Alignment of the text within the container. Defaults to [TextAlign.Start].
     * @param color The [ColorProducer] that provides the text color.
     * @param fontSize Size of the text font. Defaults to 15.sp.
     * @param textDecoration Decorations (e.g. underline/strike-through) to apply. Defaults to [TextDecoration.None].
     * @param fontWeight Thickness of the text font. Defaults to [FontWeight.Normal].
     * @param onTextLayout Callback invoked when the text layout is computed.
     */
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

    /**
     * Renders text constrained to a single line, clipping with ellipsis or custom overflow.
     *
     * @param modifier The [Modifier] to apply to the text layout.
     * @param text The plain text string to render.
     * @param textAlign Alignment of the text within the container. Defaults to [TextAlign.Start].
     * @param color Color of the text. If null, standard styling is used.
     * @param textDecoration Decorations (e.g. underline/strike-through) to apply. Defaults to [TextDecoration.None].
     * @param fontSize Size of the text font. Defaults to 15.sp.
     * @param overflow Visual overflow style to use when text overflows single line. Defaults to [TextOverflow.Ellipsis].
     * @param fontWeight Thickness of the text font. Defaults to [FontWeight.Normal].
     * @param onTextLayout Callback invoked when the text layout is computed.
     */
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

    /**
     * Renders annotated string constrained to a single line.
     *
     * @param modifier The [Modifier] to apply to the text layout.
     * @param text The [AnnotatedString] containing styled text runs.
     * @param textAlign Alignment of the text within the container. Defaults to [TextAlign.Start].
     * @param color Color of the text. If null, standard styling is used.
     * @param fontSize Size of the text font. Defaults to 15.sp.
     * @param fontWeight Thickness of the text font. Defaults to [FontWeight.Normal].
     * @param onTextLayout Callback invoked when the text layout is computed.
     */
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

    /**
     * Renders a single-line text that automatically scrolls horizontally (marquee) if it
     * exceeds its layout boundaries.
     *
     * @param modifier The [Modifier] to apply to the text layout.
     * @param text The plain text string to render.
     * @param textAlign Alignment of the text within the container. Defaults to [TextAlign.Start].
     * @param color Color of the text. If null, standard styling is used.
     * @param fontSize Size of the text font. Defaults to 15.sp.
     * @param fontWeight Thickness of the text font. Defaults to [FontWeight.Normal].
     * @param onTextLayout Callback invoked when the text layout is computed.
     */
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

    /**
     * Renders a marquee-scrolling single-line text using a dynamic [ColorProducer].
     *
     * @param modifier The [Modifier] to apply to the text layout.
     * @param text The plain text string to render.
     * @param textAlign Alignment of the text within the container. Defaults to [TextAlign.Start].
     * @param color The [ColorProducer] that provides the text color.
     * @param fontSize Size of the text font. Defaults to 15.sp.
     * @param fontWeight Thickness of the text font. Defaults to [FontWeight.Normal].
     * @param onTextLayout Callback invoked when the text layout is computed.
     */
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

    /**
     * Renders text that dynamically auto-sizes to fit the constraints of its layout container.
     *
     * @param modifier The [Modifier] to apply to the text layout.
     * @param text The plain text string to render.
     * @param color The [ColorProducer] that provides the text color.
     * @param textAlign Alignment of the text within the container. Defaults to [TextAlign.Start].
     * @param fontSize Base size of the text font. Defaults to 15.sp.
     * @param fontWeight Thickness of the text font. Defaults to [FontWeight.Normal].
     * @param autoSize The [TextAutoSize] logic configuration.
     * @param onTextLayout Callback invoked when the text layout is computed.
     */
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