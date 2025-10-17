package feo.health.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import feo.health.ui.R

internal val fontFamily = FontFamily(
    Font(resId = R.font.rubik_bold, weight = FontWeight.Bold),
    Font(resId = R.font.rubik_medium, weight = FontWeight.Medium),
    Font(resId = R.font.rubik_regular, weight = FontWeight.Normal),
    Font(resId = R.font.rubik_semibold, weight = FontWeight.SemiBold)
)

object HealthTypography {
    val medium: TextStyle = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal
    )
}