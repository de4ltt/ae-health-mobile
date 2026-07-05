package feo.health.catalog.presentation.util

import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith

/**
 * Helper object storing standard animations and transitions used across the catalog screens.
 */
object CatalogAnimation {
    /**
     * Animation definition for when the search bar enters the screen view.
     */
    val searchBarEnter
        get() = fadeIn(tween(1200)) +
                expandIn(tween(600)) +
                expandVertically(tween(600))
    /**
     * Animation definition for when the search bar exits the screen view.
     */
    val searchBarExit
        get() = fadeOut(tween(600)) +
                shrinkOut(tween(600)) +
                shrinkVertically(tween(600))

    /**
     * Content transition spec used when switching between items within a layout.
     */
    val betweenItemsDisplay = (fadeIn()).togetherWith(fadeOut(tween()))
    /**
     * Slide/fade/scale transition spec used when navigating between catalog screen destinations.
     */
    val betweenScreens = (fadeIn(animationSpec = tween(220, delayMillis = 90)) +
            scaleIn(initialScale = 0.92f, animationSpec = tween(220, delayMillis = 90)))
        .togetherWith(fadeOut(animationSpec = tween(90)))
}