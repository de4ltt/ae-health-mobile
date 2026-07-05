package feo.health.ui.util

import androidx.compose.runtime.Composable

/**
 * Contract interface implemented by screens that support rendering custom overlay loading views.
 */
interface ILoading {
    /**
     * Renders the loading overlay screen.
     *
     * @param params Vararg parameter bundle containing visual configurations or layouts.
     */
    @Composable
    fun LoadingScreen(vararg params: Any)
}