package feo.health.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * The primary application theme composable that handles light/dark mode selection
 * and resolves Android 12+ dynamic Material3 system colors if enabled.
 *
 * @param darkTheme Forces the app theme to render dark mode. Defaults to [isSystemInDarkTheme].
 * @param dynamicColor Allows loading dynamic Material3 color palettes from the Android system environment.
 * @param content Target Composable block wrapped inside theme configurations.
 */
@Composable
fun AeHealthMobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor -> {
            val context = LocalContext.current
            val m3Colors = if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            HColorScheme(
                primary = m3Colors.primary,
                secondary = m3Colors.secondary,
                background = m3Colors.background,
                onBackground = m3Colors.onBackground,
                onBackgroundContainer = m3Colors.surfaceVariant
            )
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    HealthTheme(colorScheme = colorScheme, content = content)
}