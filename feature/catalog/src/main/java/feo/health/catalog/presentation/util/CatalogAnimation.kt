package feo.health.catalog.presentation.util

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable

object CatalogAnimation {
    val searchBarEnter
        get() = fadeIn(tween(1200)) +
                expandIn(tween(600)) +
                expandVertically(tween(600))
    val searchBarExit
        get() = fadeOut(tween(600)) +
                shrinkOut(tween(600)) +
                shrinkVertically(tween(600))

    val betweenItemsDisplay = (fadeIn()).togetherWith(fadeOut(tween()))
    val betweenScreens = (fadeIn(animationSpec = tween(220, delayMillis = 90)) +
            scaleIn(initialScale = 0.92f, animationSpec = tween(220, delayMillis = 90)))
        .togetherWith(fadeOut(animationSpec = tween(90)))
}