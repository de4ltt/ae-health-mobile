package feo.health.ui.component.container

import android.content.Context
import android.net.ConnectivityManager
import android.text.Layout
import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
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
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.ImageLoader
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import feo.health.ui.component.HProgressIndicator
import feo.health.ui.component.HText
import feo.health.ui.image_loader.HImageLoader
import feo.health.ui.theme.HTheme
import feo.health.ui.util.HConnectivityChecker
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import java.io.File

object HContainer {

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

    object Image {

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