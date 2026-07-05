package feo.health.ai.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import feo.health.ai.presentation.AiScreen
import feo.health.ai.presentation.viewmodel.AiViewModel
import feo.health.ui.navigation.AiRoute
import feo.health.ui.navigation.Routes

/**
 * Extension on [NavGraphBuilder] configuring route navigation mappings targeting the AI assistant features.
 * Supporting both string-based and type-safe route matching mappings.
 *
 * @param navHostController Router navigation controller interface helper.
 * @param aiViewModel View model containing screen states and event processors.
 */
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