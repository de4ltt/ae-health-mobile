package feo.health.ui.component.container

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import feo.health.ui.component.HText
import feo.health.ui.theme.HTheme

object HOptionSelector {

    @Composable
    fun <T> SingleLine(
        options: List<T>,
        selectedOption: T,
        onOptionSelected: (T) -> Unit,
        extractName: @Composable (T) -> String,
        modifier: Modifier = Modifier,
        contentPadding: PaddingValues = PaddingValues(4.dp),
        containerColor: Color = HTheme.colors.onBackgroundContainer,
        selectedContainerColor: Color = HTheme.colors.background,
        unselectedColor: Color = HTheme.colors.secondary,
        selectedColor: Color = HTheme.colors.secondary,
    ) {
        require(options.isNotEmpty() && selectedOption in options)

        val density = LocalDensity.current
        var selectedOffset by remember { mutableStateOf(0.dp) }
        var selectedWidth by remember { mutableStateOf(0.dp) }

        val animatedOffset by animateDpAsState(selectedOffset, label = "offset")
        val animatedWidth by animateDpAsState(selectedWidth, label = "width")

        Layout(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .clip(HTheme.shapes.circular)
                .background(containerColor)
                .clickable(indication = null, interactionSource = null) {}
                .padding(contentPadding),
            content = {
                Box(
                    modifier = Modifier
                        .offset(x = animatedOffset)
                        .width(animatedWidth)
                        .fillMaxHeight()
                        .background(selectedContainerColor, HTheme.shapes.circular)
                )
                options.forEach { option ->
                    val isSelected = option == selectedOption
                    Box(
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { onOptionSelected(option) }
                            )
                            .onGloballyPositioned { coordinates ->
                                if (isSelected) {
                                    selectedOffset = with(density) { coordinates.positionInParent().x.toDp() }
                                    selectedWidth = with(density) { coordinates.size.width.toDp() }
                                }
                            }
                            .padding(horizontal = 24.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        HText.SingleLine(
                            text = extractName(option),
                            color = if (isSelected) selectedColor else unselectedColor,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        ) { measurables, constraints ->
            val placeables = measurables.map { it.measure(constraints.copy(minWidth = 0)) }
            val indicator = placeables[0]
            val items = placeables.drop(1)

            val totalWidth = constraints.maxWidth
            val height = items.maxOf { it.height } + 8

            val spacingPx = (totalWidth - items.sumOf { it.width }) / (items.size - 1)
            val usedWidth = items.sumOf { it.width } + spacingPx * (items.size - 1)
            val startX = (totalWidth - usedWidth) / 2

            layout(totalWidth, height) {
                var x = startX
                indicator.placeRelative(0, 4)
                items.forEach { placeable ->
                    placeable.placeRelative(x, 4)
                    x += placeable.width + spacingPx
                }
            }
        }
    }
}