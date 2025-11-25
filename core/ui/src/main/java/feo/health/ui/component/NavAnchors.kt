package feo.health.ui.component

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import feo.health.ui.component.container.HList
import feo.health.ui.navigation.Routes
import feo.health.ui.resource.HIcons
import feo.health.ui.theme.HTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

object NavAnchors {

    private val _isShown: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val isShown = _isShown.asStateFlow()

    fun show() {
        _isShown.value = true
    }

    fun hide() {
        _isShown.value = false
    }

    @Composable
    operator fun invoke(
        navHostController: NavHostController
    ) {

        val isShown by isShown.collectAsStateWithLifecycle()
        var currentDestination by remember { mutableStateOf("") }

        LaunchedEffect(Unit) {
            navHostController.currentBackStackEntryFlow.collect { entry ->
                currentDestination = entry.destination.route ?: ""
            }
        }

        AnimatedVisibility(
            visible = isShown,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Destinations.entries.forEach {
                    it.icon.invoke(
                        modifier = Modifier.Companion.clickable(
                            onClick = {
                                currentDestination = it.destination
                                navHostController.navigate(it.destination)
                            },
                            indication = null,
                            interactionSource = null
                        ),
                        tint = if (currentDestination == it.destination) HTheme.colors.primary else
                            HTheme.colors.onBackground
                    )
                }
            }
        }
    }

    private enum class Destinations(
        val icon: HIcons,
        val destination: String
    ) {
        HOME(icon = HIcons.HOME, destination = Routes.catalog),
        AI(icon = HIcons.AI, destination = Routes.ai),
        PROFILE(icon = HIcons.USER, destination = Routes.user),
    }
}