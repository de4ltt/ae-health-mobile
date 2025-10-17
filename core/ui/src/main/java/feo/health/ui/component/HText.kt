package feo.health.ui.component

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import feo.health.ui.theme.fontFamily

object HText {

    @Composable
    private operator fun invoke(
        modifier: Modifier = Modifier,
        text: String,
        color: ColorProducer? = null,
        fontSize: TextUnit = 15.sp,
        fontWeight: FontWeight? = FontWeight.Normal,
        maxLines: Int = Int.MAX_VALUE,
        autoSize: TextAutoSize? = null,
        basicMarquee: Boolean = false
    ) = BasicText(
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
            fontSize = fontSize,
            fontFamily = fontFamily,
            fontWeight = fontWeight
        )
    )

    @Composable
    fun Default(
        modifier: Modifier = Modifier,
        text: String,
        color: ColorProducer? = null,
        fontSize: TextUnit = 15.sp,
        fontWeight: FontWeight? = FontWeight.Normal,
    ) = invoke(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight
    )

    @Composable
    fun SingleLine(
        modifier: Modifier = Modifier,
        text: String,
        color: ColorProducer? = null,
        fontSize: TextUnit = 15.sp,
        fontWeight: FontWeight? = FontWeight.Normal,
    ) = invoke(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        maxLines = 1
    )

    @Composable
    fun BasicMarquee(
        modifier: Modifier = Modifier,
        text: String,
        color: ColorProducer? = null,
        fontSize: TextUnit = 15.sp,
        fontWeight: FontWeight? = FontWeight.Normal
    ) = invoke(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        maxLines = 1,
        basicMarquee = true
    )

    @Composable
    fun AutoSize(
        modifier: Modifier = Modifier,
        text: String,
        color: ColorProducer? = null,
        fontSize: TextUnit = 15.sp,
        fontWeight: FontWeight? = FontWeight.Normal,
        autoSize: TextAutoSize
    ) = invoke(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        maxLines = 1,
        autoSize = autoSize
    )
}