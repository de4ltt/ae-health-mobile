package feo.health.mobile.navigation

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import feo.health.ai.presentation.navigation.aiNavGraph
import feo.health.ai.presentation.viewmodel.AiViewModel
import feo.health.auth.presentation.navigation.authNavGraph
import feo.health.auth.presentation.viewmodel.AuthViewModel
import feo.health.catalog.presentation.navigation.catalogNavGraph
import feo.health.catalog.presentation.viewmodel.CatalogViewModel
import feo.health.mobile.AEHealthApp
import feo.health.mobile.viewmodel.HViewModelProvider
import feo.health.ui.navigation.Routes
import feo.health.user.components.presentation.navigation.userNavGraph
import feo.health.user.components.presentation.viewmodel.UserViewModel

@SuppressLint("RestrictedApi")
@Composable
internal fun HNavHost(
    modifier: Modifier = Modifier,
    startDestination: String,
    navHostController: NavHostController,
    viewModelProvider: HViewModelProvider
) = NavHost(
    modifier = modifier,
    navController = navHostController,
    startDestination = startDestination
) {
    catalogNavGraph(
        navHostController = navHostController,
        catalogViewModel = viewModelProvider<CatalogViewModel>()
    )
    authNavGraph(
        navHostController = navHostController,
        authViewModel = viewModelProvider<AuthViewModel>()
    )
    userNavGraph(
        navHostController = navHostController,
        userViewModel = viewModelProvider<UserViewModel>()
    )
    aiNavGraph(
        navHostController = navHostController,
        aiViewModel = viewModelProvider<AiViewModel>()
    )
}