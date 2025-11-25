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
import com.valentinilk.shimmer.ShimmerTheme
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import feo.health.ui.theme.HTheme


sealed class HProgressIndicator(
    val animation: @Composable (Modifier, Color, Color) -> Unit
) {
    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        color: Color = HTheme.colors.primary,
        containerColor: Color = HTheme.colors.onBackgroundContainer
    ) = animation(modifier, containerColor, color)

    object Circular : HProgressIndicator({ modifier, containerColor, color ->
        Box(modifier = modifier.background(containerColor), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier,
                color = color
            )
        }
    })

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