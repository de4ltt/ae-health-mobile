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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import feo.health.ui.theme.HTheme

/**
 * A custom button component that provides consistent styling, shapes, and colors
 * according to the application's design system ([HTheme]).
 *
 * It provides two variants: [Default] and [Selectable].
 */
object HButton {

    /**
     * Core layout container of the button.
     * Handles background, shape, click events, and content arrangement.
     *
     * @param modifier The [Modifier] to apply to the button.
     * @param buttonColors The color scheme configuration of the button.
     * @param buttonShape The background shape.
     * @param contentPadding Padding applied inside the button content container.
     * @param enabled Decides if the button is interactive.
     * @param onClick Callback triggered on click event.
     * @param content The inner composable content of the button.
     */
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
        modifier = modifier
            .background(
                color = if (enabled) buttonColors.containerColor
                else buttonColors.disabledContainerColor,
                shape = buttonShape
            )
            .clickable(
                enabled = enabled,
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = androidx.compose.foundation.LocalIndication.current
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            content(
                if (enabled) buttonColors.contentColor
                else buttonColors.disabledContentColor
            )
        }
    }

    /**
     * A selectable button variant that supports enabled/disabled states with custom
     * container and content colors for each state.
     *
     * @param modifier The [Modifier] to be applied to this button.
     * @param buttonShape The [Shape] of the button's background. Defaults to [HTheme.shapes.rounded12].
     * @param contentPadding The padding values to apply inside the button. Defaults to [HTheme.padding.common].
     * @param enabled Controls the enabled state of this button. When `false`, the button is not clickable and uses disabled colors. Defaults to `false`.
     * @param containerColor The background color of the button when enabled. Defaults to [HTheme.colors.primary].
     * @param contentColor The preferred color for content inside the button when enabled. Defaults to [HTheme.colors.background].
     * @param disabledContainerColor The background color of the button when disabled. Defaults to [HTheme.colors.disabledContainer].
     * @param disabledContentColor The preferred color for content inside the button when disabled. Defaults to [HTheme.colors.disabledContent].
     * @param onClick The callback to be invoked when the button is clicked.
     * @param content The composable content layout of the button, providing the resolved content [Color] for styling text and icons.
     */
    @Composable
    fun Selectable(
        modifier: Modifier = Modifier,
        buttonShape: Shape = HTheme.shapes.rounded12,
        contentPadding: PaddingValues = HTheme.padding.common,
        enabled: Boolean = false,
        containerColor: Color = HTheme.colors.primary,
        contentColor: Color = HTheme.colors.background,
        disabledContainerColor: Color = HTheme.colors.disabledContainer,
        disabledContentColor: Color = HTheme.colors.disabledContent,
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

    /**
     * A default button variant that is always clickable and uses standard theme colors.
     *
     * @param modifier The [Modifier] to be applied to this button.
     * @param buttonShape The [Shape] of the button's background. Defaults to [HTheme.shapes.rounded12].
     * @param contentPadding The padding values to apply inside the button. Defaults to [HTheme.padding.common].
     * @param containerColor The background color of the button. Defaults to [HTheme.colors.primary].
     * @param contentColor The preferred color for content inside the button. Defaults to [HTheme.colors.background].
     * @param onClick The callback to be invoked when the button is clicked.
     * @param content The composable content layout of the button, providing the resolved content [Color] for styling text and icons.
     */
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