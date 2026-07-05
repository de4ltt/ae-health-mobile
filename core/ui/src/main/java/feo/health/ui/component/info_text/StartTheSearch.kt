package feo.health.ui.component.info_text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import feo.health.ui.R
import feo.health.ui.component.HText
import feo.health.ui.util.capitalize

/**
 * Visual screen component instructing the user to type in the input field to initiate a query.
 */
object StartTheSearch {

    /**
     * Renders the start search view layout.
     *
     * @param modifier The [Modifier] to apply to the screen box.
     */
    @Composable
    operator fun invoke(modifier: Modifier = Modifier) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Companion.Center) {
            HText.Default(text = stringResource(R.string.start_the_search).capitalize())
        }
    }
}