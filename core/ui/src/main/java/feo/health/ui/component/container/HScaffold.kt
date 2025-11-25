package feo.health.ui.component.container

import android.widget.Space
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import feo.health.ui.component.HToast
import feo.health.ui.component.NavAnchors
import feo.health.ui.theme.HTheme
import kotlinx.coroutines.flow.StateFlow
import java.nio.file.WatchEvent

object HScaffold {

    @Composable
    fun Default(
        modifier: Modifier = Modifier,
        topBar: @Composable () -> Unit = {},
        bottomBar: @Composable () -> Unit = {},
        content: @Composable () -> Unit
    ) {
        Column(modifier = modifier) {
            topBar()
            content()
            bottomBar()
        }
    }

    @Composable
    fun Main(
        modifier: Modifier,
        navHostController: NavHostController,
        topBar: @Composable () -> Unit = {},
        content: @Composable (Modifier) -> Unit
    ) = Scaffold(
        topBar = topBar,
        modifier = modifier,
        containerColor = HTheme.colors.background,
        bottomBar = { NavAnchors(navHostController = navHostController) },
        content = { contentPadding ->
            Box(
                modifier = Modifier.padding(contentPadding)
            ) {
                content(Modifier.fillMaxSize())
                HToast.Toast(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }
    )
}