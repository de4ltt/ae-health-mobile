package feo.health.ui.theme

import androidx.compose.ui.graphics.Color

data class HColorScheme(
    val primary: Color = Color(0xFF2563EB),          // Premium Slate Blue
    val secondary: Color = Color(0xFF4F46E5),        // Soft Indigo
    val background: Color = Color(0xFFF8FAFC),       // Clean Off-White
    val onBackground: Color = Color(0xFF0F172A),     // Deep Charcoal
    val onBackgroundContainer: Color = Color(0xFFE2E8F0), // Gray Slate Border/Surface
    val disabledContainer: Color = Color(0xFFE2E8F0),     // Gray Slate for Disabled Container
    val disabledContent: Color = Color(0xFF94A3B8)       // Muted Gray for Disabled Content
) {
    object Additional {
        val BLUE = Color(23, 133, 242, 255)
        val ORANGE: Color = Color(176, 120, 0, 255)
        val GREEN: Color = Color(2, 136, 0, 255)
        val RED: Color = Color(145, 0, 0, 255)
        val TRANSPARENT = Color(0, 0, 0, 0)
    }
}

val LightColorScheme = HColorScheme()

val DarkColorScheme = HColorScheme(
    primary = Color(0xFF3B82F6),          // Vibrant Neon Blue
    secondary = Color(0xFF6366F1),        // Indigo Light
    background = Color(0xFF0B0F19),       // Midnight Obsidian
    onBackground = Color(0xFFF8FAFC),     // Silver White
    onBackgroundContainer = Color(0xFF1E293B), // Dark Container Slate
    disabledContainer = Color(0xFF1E293B),     // Dark Slate for Disabled Container
    disabledContent = Color(0xFF64748B)       // Muted Dark Gray for Disabled Content
)