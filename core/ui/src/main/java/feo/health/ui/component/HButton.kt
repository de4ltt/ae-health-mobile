package feo.health.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import feo.health.ui.theme.HTheme

object HButton {

    @Composable
    private operator fun invoke(
        modifier: Modifier,
        buttonColors: ButtonColors,
        buttonShape: Shape,
        contentPadding: PaddingValues,
        enabled: Boolean = true,
        onClick: () -> Unit,
        content: @Composable RowScope.(Color) -> Unit,
    ) = Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = if (enabled) buttonColors.containerColor
                else buttonColors.disabledContainerColor,
                shape = buttonShape
            )
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier.padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            content(
                if (enabled) buttonColors.contentColor
                else buttonColors.disabledContentColor
            )
        }
    }

    @Composable
    fun Selectable(
        modifier: Modifier = Modifier,
        buttonShape: Shape = HTheme.shapes.rounded12,
        contentPadding: PaddingValues = HTheme.padding.common,
        enabled: Boolean = false,
        containerColor: Color = HTheme.colors.primary,
        contentColor: Color = HTheme.colors.background,
        disabledContainerColor: Color = HTheme.colors.onBackground,
        disabledContentColor: Color = HTheme.colors.secondary,
        onClick: () -> Unit = {},
        content: @Composable RowScope.(Color) -> Unit
    ) = invoke(
        modifier = modifier,
        buttonColors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        ),
        buttonShape = buttonShape,
        contentPadding = contentPadding,
        enabled = enabled,
        onClick = onClick,
        content = content
    )

    @Composable
    fun Default(
        modifier: Modifier = Modifier,
        buttonShape: Shape = HTheme.shapes.rounded12,
        contentPadding: PaddingValues = HTheme.padding.common,
        containerColor: Color = HTheme.colors.primary,
        contentColor: Color = HTheme.colors.background,
        onClick: () -> Unit = {},
        content: @Composable RowScope.(Color) -> Unit
    ) = invoke(
        modifier = modifier,
        buttonColors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        buttonShape = buttonShape,
        contentPadding = contentPadding,
        onClick = onClick,
        content = content
    )
}