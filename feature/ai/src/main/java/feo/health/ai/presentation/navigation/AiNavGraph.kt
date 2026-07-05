package feo.health.ai.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import feo.health.ai.presentation.AiScreen
import feo.health.ai.presentation.viewmodel.AiViewModel
import feo.health.ui.navigation.Routes
import feo.health.ui.navigation.AiRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.aiNavGraph(
    navHostController: NavHostController,
    aiViewModel: AiViewModel
) {
    // String-based route for compatibility
    composable(route = Routes.ai) {
        AiScreen(
            navHostController = navHostController,
            aiViewModel = aiViewModel
        )
    }

    // Type-safe route
    composable<AiRoute> {
        AiScreen(
            navHostController = navHostController,
            aiViewModel = aiViewModel
        )
    }
}