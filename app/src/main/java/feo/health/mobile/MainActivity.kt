package feo.health.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import feo.health.auth.presentation.component.Authorization
import feo.health.mobile.navigation.HNavHost
import feo.health.ui.component.HToast
import feo.health.ui.component.container.HScaffold
import feo.health.ui.navigation.Routes
import feo.health.ui.theme.AeHealthMobileTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private var isAuthorized by mutableStateOf<Boolean?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            (application as AEHealthApp).dataStore.accessTokenFlow.collect { token ->
                isAuthorized = token?.isNotEmpty() ?: false
            }
        }

        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()

            AeHealthMobileTheme {
                isAuthorized?.let { authorized ->

                    val startDestination = if (authorized) Routes.catalog else Routes.auth

                    LaunchedEffect(authorized) {
                        if (!authorized) {
                            navHostController.navigate(Routes.auth) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    }

                    HScaffold.Main(
                        modifier = Modifier,
                        navHostController = navHostController
                    ) { modifier ->
                        HNavHost(
                            modifier = modifier
                                .padding(horizontal = 15.dp)
                                .padding(top = 15.dp),
                            navHostController = navHostController,
                            startDestination = startDestination
                        )
                    }
                }
            }
        }
    }
}