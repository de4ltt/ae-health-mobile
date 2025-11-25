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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import feo.health.auth.presentation.component.Authorization
import feo.health.mobile.navigation.HNavHost
import feo.health.mobile.viewmodel.HViewModelProvider
import feo.health.ui.component.HToast
import feo.health.ui.component.container.HScaffold
import feo.health.ui.navigation.Routes
import feo.health.ui.theme.AeHealthMobileTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {

    var isAuthorized: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        runBlocking {
            isAuthorized =
                (application as AEHealthApp).dataStore.getAccessToken()?.isNotEmpty() ?: false
        }

        val viewModelProvider = HViewModelProvider(this)


        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()

            AeHealthMobileTheme {
                isAuthorized?.let {

                    val startDestination = if (it) Routes.catalog else Routes.auth

                    HScaffold.Main(
                        modifier = Modifier,
                        navHostController = navHostController
                    ) { modifier ->
                        HNavHost(
                            modifier = modifier
                                .padding(horizontal = 15.dp)
                                .padding(top = 15.dp),
                            navHostController = navHostController,
                            viewModelProvider = viewModelProvider,
                            startDestination = startDestination
                        )
                    }
                }
            }
        }
    }
}