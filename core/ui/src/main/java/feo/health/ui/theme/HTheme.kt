package feo.health.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Composition local providing the active [HColorScheme] tokens.
 */
val LocalColorScheme = staticCompositionLocalOf { HColorScheme() }

/**
 * Composition local providing the active [HShapes] tokens.
 */
val LocalShapes = staticCompositionLocalOf { HShapes }

/**
 * Composition local providing the active [HealthTypography] tokens.
 */
val LocalTypography = staticCompositionLocalOf { HealthTypography }

/**
 * Composition local providing the active [HPadding] tokens.
 */
val LocalPadding = staticCompositionLocalOf { HPadding }

/**
 * Custom styling provider wrapper that injects custom color, shape, typography,
 * and padding context properties into the Compose layout tree.
 *
 * @param colorScheme Color palette configuration. Defaults to Light scheme.
 * @param shapes Border corner shapes configurations.
 * @param typography Text fonts and sizing specifications.
 * @param padding Gap and spacing padding configs.
 * @param content Inside composable layouts scope.
 */
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

/**
 * Global entry helper object used to access the styling properties of the active theme in Composables.
 */
object HTheme {
    /**
     * Active theme color scheme details.
     */
    val colors: HColorScheme
        @Composable
        get() = LocalColorScheme.current

    /**
     * Active theme layout shapes definitions.
     */
    val shapes: HShapes
        @Composable
        get() = LocalShapes.current

    /**
     * Active theme typography scale configurations.
     */
    val typography: HealthTypography
        @Composable
        get() = LocalTypography.current

    /**
     * Active theme container padding layout metrics.
     */
    val padding: HPadding
        @Composable
        get() = LocalPadding.current
}



