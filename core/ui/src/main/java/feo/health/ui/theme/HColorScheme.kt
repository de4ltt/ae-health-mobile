package feo.health.ui.theme

import androidx.compose.ui.graphics.Color

data class HColorScheme(
    val primary: Color = BLUE,
    val secondary: Color = DIZZY_BLUE,
    val background: Color = WHITE,
    val onBackground: Color = BLACK,
    val onBackgroundContainer: Color = LIGHT_GRAY
) {
    object Additional {
        val BLUE = feo.health.ui.theme.BLUE
        val ORANGE: Color = feo.health.ui.theme.ORANGE
        val GREEN: Color = feo.health.ui.theme.GREEN
        val RED: Color = feo.health.ui.theme.RED
        val TRANSPARENT = feo.health.ui.theme.TRANSPARENT
    }
}