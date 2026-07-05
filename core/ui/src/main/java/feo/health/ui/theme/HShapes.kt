package feo.health.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Layout clip shape tokens representing rounded, circular, and rectangular geometric boundaries.
 */
object HShapes {
    /**
     * Standard rounded corner shape with 12.dp radius.
     */
    val rounded12: RoundedCornerShape = RoundedCornerShape(12.dp)

    /**
     * Fully circular shape used for round buttons or badges.
     */
    val circular: RoundedCornerShape = CircleShape

    /**
     * Flat rectangular shape with sharp corners.
     */
    val rectangular: Shape = RectangleShape
}
