package feo.health.ai.presentation

import android.content.Intent
import android.provider.Browser
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import feo.health.ai.presentation.component.AiContainer
import feo.health.ai.presentation.viewmodel.AiViewModel
import feo.health.ai.presentation.viewmodel.companion.AiEvent
import feo.health.ui.component.NavAnchors
import feo.health.ui.navigation.Routes

private const val TAG_DOCTOR = "doctor"
private const val TAG_DISEASE = "disease"
private const val SEARCH_URI = "https://www.google.com/search?q="

@Composable
fun AiScreen(
    navHostController: NavHostController,
    aiViewModel: AiViewModel
) {

    BackHandler {
        if (aiViewModel.isEmpty)
            navHostController.popBackStack()
        else aiViewModel.onEvent(AiEvent.OnBack)
    }

    LaunchedEffect(Unit) {
        NavAnchors.show()
    }

    val context = LocalContext.current

    AiContainer.Default(
        modifier = Modifier.fillMaxSize(),
        state = aiViewModel.screenState,
        onEvent = aiViewModel::onEvent,
        onTextClick = { tag, name ->
            when {
                tag.equals(TAG_DOCTOR, ignoreCase = true) -> {
                    navHostController.navigate(Routes.catalog + "/$name")
                }

                tag.equals(TAG_DISEASE, ignoreCase = true) -> {
                    val intent = Intent(Intent.ACTION_VIEW,
                        "$SEARCH_URI$name".toUri())
                    intent.putExtra(Browser.EXTRA_APPLICATION_ID, context.packageName)
                    context.startActivity(intent)
                }
            }
        }
    )
}