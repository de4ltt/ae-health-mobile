package feo.health.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalColorScheme = staticCompositionLocalOf { HColorScheme() }
val LocalShapes = staticCompositionLocalOf { HShapes }
val LocalTypography = staticCompositionLocalOf { HealthTypography }
val LocalPadding = staticCompositionLocalOf { HPadding }

@Composable
fun HealthTheme(
    colorScheme: HColorScheme = HColorScheme(),
    shapes: HShapes = HShapes,
    typography: HealthTypography = HealthTypography,
    padding: HPadding = HPadding,
    content: @Composable () -> Unit
) {
    @Suppress("DEPRECATION_ERROR")
    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalShapes provides shapes,
        LocalTypography provides typography,
        LocalPadding provides padding,
        content = content
    )
}

object HTheme {
    val colors: HColorScheme
        @Composable
        get() = LocalColorScheme.current

    val shapes: HShapes
        @Composable
        get() = LocalShapes.current

    val typography: HealthTypography
        @Composable
        get() = LocalTypography.current

    val padding: HPadding
        @Composable
        get() = LocalPadding.current

}



