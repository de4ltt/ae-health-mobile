package feo.health.ui.component.container

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import feo.health.ui.component.HToast
import feo.health.ui.component.NavAnchors
import feo.health.ui.theme.HTheme

/**
 * Screen layout wrappers managing status header bars, navigation anchors,
 * background themes, and system alerts.
 */
object HScaffold {

    /**
     * Renders a basic vertical screen scaffold column.
     *
     * @param modifier The [Modifier] applied to the outer layout container.
     * @param topBar Composable header layout.
     * @param bottomBar Composable footer layout.
     * @param content Composable body layout.
     */
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

    /**
     * Renders the primary application screen layout, setting background themes,
     * embedding bottom navigation, and overlaying toast alerts.
     *
     * @param modifier The [Modifier] applied to the Scaffold wrapper.
     * @param navHostController The active navigation host controller.
     * @param topBar Composable header layout.
     * @param content Composable body layout receiving content padding Modifiers.
     */
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