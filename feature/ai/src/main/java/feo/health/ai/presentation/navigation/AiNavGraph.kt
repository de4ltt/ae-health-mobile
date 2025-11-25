package feo.health.ai.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import feo.health.ai.presentation.AiScreen
import feo.health.ai.presentation.viewmodel.AiViewModel
import feo.health.ui.navigation.Routes

fun NavGraphBuilder.aiNavGraph(
    navHostController: NavHostController,
    aiViewModel: AiViewModel
) {
    composable(route = Routes.ai) {
        AiScreen(
            navHostController = navHostController,
            aiViewModel = aiViewModel
        )
    }
}