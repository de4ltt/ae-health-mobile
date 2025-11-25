package feo.health.ui.util

import androidx.compose.runtime.Composable

interface ILoading {
    @Composable
    fun LoadingScreen(vararg params: Any)
}