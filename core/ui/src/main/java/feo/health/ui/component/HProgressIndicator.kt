package feo.health.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.valentinilk.shimmer.LocalShimmerTheme
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import feo.health.ui.theme.HTheme


/**
 * Represents custom progress indicators and skeleton loaders used throughout the application.
 *
 * It provides two variants: [Circular] and [Shimmer].
 *
 * @property animation Composable block that draws the loader.
 */
sealed class HProgressIndicator(
    val animation: @Composable (Modifier, Color, Color) -> Unit
) {
    /**
     * Renders the loading animation layout.
     *
     * @param modifier The [Modifier] to apply to the progress layout.
     * @param color Primary color of the indicator or shimmer path. Defaults to [HTheme.colors.primary].
     * @param containerColor Background/container color of the layout. Defaults to [HTheme.colors.onBackgroundContainer].
     */
    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        color: Color = HTheme.colors.primary,
        containerColor: Color = HTheme.colors.onBackgroundContainer
    ) = animation(modifier, containerColor, color)

    /**
     * A standard circular spinner indicator centered within a background box container.
     */
    object Circular : HProgressIndicator({ modifier, containerColor, color ->
        Box(modifier = modifier.background(containerColor), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier,
                color = color
            )
        }
    })

    /**
     * A shimmer skeleton loader indicator used for content placeholder loading screens.
     */
    object Shimmer : HProgressIndicator({ modifier, containerColor, color ->
        val theme = LocalShimmerTheme.current.copy(
            shaderColors = listOf(
                containerColor.copy(alpha = 0.25f),
                containerColor.copy(alpha = 1f),
                containerColor.copy(alpha = 0.25f)
            )
        )
        val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.View, theme = theme)
        Box(
            modifier = modifier
                .shimmer(shimmer)
                .background(color)
        )
    }) {
        /**
         * Resolves a default shimmer instance with standard theme background colors.
         */
        val defaultShimmer
            @Composable get() = rememberShimmer(
                shimmerBounds = ShimmerBounds.View, theme = LocalShimmerTheme.current.copy(
                    shaderColors = listOf(
                        HTheme.colors.background.copy(alpha = 0.5f),
                        HTheme.colors.background.copy(alpha = 1f),
                        HTheme.colors.background.copy(alpha = 0.5f)
                    )
                )
            )
    }
}