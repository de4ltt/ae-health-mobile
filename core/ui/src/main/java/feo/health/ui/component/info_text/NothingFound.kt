package feo.health.ui.component.info_text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import feo.health.ui.component.HText
import feo.health.ui.resource.HStrings
import feo.health.ui.resource.HStrings.capitalize

object NothingFound {

    @Composable
    operator fun invoke(modifier: Modifier = Modifier) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Companion.Center) {
            HText.Default(text = HStrings.nothingFound.capitalize())
        }
    }
}