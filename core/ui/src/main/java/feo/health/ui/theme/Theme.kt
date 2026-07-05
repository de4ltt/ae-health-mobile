package feo.health.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun AeHealthMobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
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