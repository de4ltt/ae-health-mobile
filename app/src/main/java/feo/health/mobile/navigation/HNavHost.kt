package feo.health.mobile.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import feo.health.ai.presentation.navigation.aiNavGraph
import feo.health.auth.presentation.navigation.authNavGraph
import feo.health.catalog.presentation.navigation.catalogNavGraph
import feo.health.mobile.AEHealthApp
import feo.health.user.components.presentation.navigation.userNavGraph

import feo.health.ai.presentation.viewmodel.AiViewModel
import feo.health.auth.presentation.viewmodel.AuthViewModel
import feo.health.catalog.presentation.viewmodel.CatalogViewModel
import feo.health.user.components.presentation.viewmodel.UserViewModel

@SuppressLint("RestrictedApi")
@Composable
internal fun HNavHost(
    modifier: Modifier = Modifier,
    startDestination: String,
    navHostController: NavHostController
) {
    val context = LocalContext.current
    val app = remember(context) { context.applicationContext as AEHealthApp }

    val catalogViewModel: CatalogViewModel = viewModel(factory = app.appComponent.catalogViewModelFactory())
    val authViewModel: AuthViewModel = viewModel(factory = app.appComponent.authViewModelFactory())
    val userViewModel: UserViewModel = viewModel(factory = app.appComponent.userViewModelFactory())
    val aiViewModel: AiViewModel = viewModel(factory = app.appComponent.aiViewModelFactory())

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
        catalogNavGraph(
            navHostController = navHostController,
            catalogViewModel = catalogViewModel
        )
        authNavGraph(
            navHostController = navHostController,
            authViewModel = authViewModel
        )
        userNavGraph(
            navHostController = navHostController,
            userViewModel = userViewModel
        )
        aiNavGraph(
            navHostController = navHostController,
            aiViewModel = aiViewModel
        )
    }
}