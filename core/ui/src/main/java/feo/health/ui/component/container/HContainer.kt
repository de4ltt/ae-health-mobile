package feo.health.ui.component.container

import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import feo.health.ui.component.HProgressIndicator
import feo.health.ui.component.HText
import feo.health.ui.image_loader.HImageLoader
import feo.health.ui.theme.HTheme
import feo.health.ui.util.HConnectivityChecker
import kotlinx.coroutines.delay

/**
 * Layout container wrapper utilities supplying standard shapes, background tints, titles,
 * and media/image loader components according to the application theme styling system.
 */
object HContainer {

    /**
     * Renders a basic content card box with standard rounded corners and padding layouts.
     *
     * @param modifier The [Modifier] to apply to the container layout.
     * @param shape Clip shape of the container borders. Defaults to [HTheme.shapes.rounded12].
     * @param backgroundColor Color tint of the container background. Defaults to [HTheme.colors.onBackgroundContainer].
     * @param spacing Vertical spacing applied between column child elements. Defaults to 10.dp.
     * @param contentAlignment Alignment of child elements within the Box. Defaults to [Alignment.Center].
     * @param paddingValues Padding applied around the inner Column block. Defaults to 15.dp.
     * @param content Composable block representing nested layout components inside the container.
     */
    @Composable
    fun Default(
        modifier: Modifier = Modifier,
        shape: Shape = HTheme.shapes.rounded12,
        backgroundColor: Color = HTheme.colors.onBackgroundContainer,
        spacing: Dp = 10.dp,
        contentAlignment: Alignment = Alignment.Center,
        paddingValues: PaddingValues = PaddingValues(15.dp),
        content: @Composable ColumnScope.() -> Unit = {}
    ) = Box(
        modifier = modifier
            .clip(shape)
            .background(color = backgroundColor),
        contentAlignment = contentAlignment,
        content = {
            Column(
                modifier = Modifier.padding(paddingValues),
                content = content,
                verticalArrangement = Arrangement.spacedBy(spacing)
            )
        },
    )

    /**
     * Renders a top-titled layout screen container with standard spacing.
     *
     * @param modifier The [Modifier] to apply to the screen.
     * @param title Title header text displayed at the top of the column.
     * @param content Composable representing the primary screen body content.
     */
    @Composable
    fun TitledScreen(
        modifier: Modifier = Modifier,
        title: String,
        content: @Composable () -> Unit
    ) = Column(modifier = modifier) {
        val spacing = 15.dp
        Spacer(modifier = Modifier.height(spacing))
        HText.Default(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(spacing.times(1.5f)))
        content()
    }

    /**
     * Nested utility object providing media loading layouts and image containers.
     */
    object Image {

        /**
         * Renders a responsive network image viewer enlisting automatic reload retry checks,
         * shimmer skeleton animations, and fallback vector icons on error.
         *
         * @param modifier The [Modifier] applied to the outer layout container.
         * @param imageModifier The [Modifier] applied to the image view or shimmer loading view itself.
         * @param progressIndicator Spinner/Loader indicator variant to display during network fetch. Defaults to [HProgressIndicator.Circular].
         * @param onError Composable to render as error fallback (e.g. status vector icons).
         * @param model Image asset location identifier (URI, resource ID, or file path).
         */
        @Composable
        fun AsyncImage(
            modifier: Modifier = Modifier,
            imageModifier: Modifier = Modifier,
            progressIndicator: HProgressIndicator = HProgressIndicator.Circular,
            onError: @Composable () -> Unit = {},
            model: Any?
        ) {

            val painter =
                rememberAsyncImagePainter(model = model, imageLoader = HImageLoader.INSTANCE)
            var imageState: AsyncImagePainter.State by remember {
                mutableStateOf(
                    AsyncImagePainter.State.Loading(
                        painter
                    )
                )
            }

            val connectivityManager = LocalContext.current
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val connectivityChecker = HConnectivityChecker(connectivityManager)
            LaunchedEffect(painter.state) {
                painter.state.collect {
                    imageState = it
                    if (it is AsyncImagePainter.State.Error) {
                        delay(5 * 1000L)
                        if (connectivityChecker.isOnline())
                            painter.restart()
                    }
                }
            }

            Box(modifier = modifier, contentAlignment = Alignment.Center) {
                AnimatedVisibility(visible = imageState !is AsyncImagePainter.State.Success) { onError() }
                Crossfade(
                    targetState = imageState
                ) {
                    when (it) {
                        is AsyncImagePainter.State.Loading -> progressIndicator(
                             modifier = imageModifier
                        )

                        is AsyncImagePainter.State.Success -> Image(
                            painter = painter,
                            contentDescription = "image",
                            contentScale = ContentScale.FillWidth,
                            modifier = imageModifier
                        )

                        else -> {}
                    }
                }
            }
        }
    }
}