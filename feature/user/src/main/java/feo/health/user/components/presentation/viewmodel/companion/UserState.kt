package feo.health.user.components.presentation.viewmodel.companion

import androidx.compose.runtime.Composable
import feo.health.ui.util.ILoading
import feo.health.user.components.presentation.model.UCatalogItem
import feo.health.user.components.presentation.model.User
import feo.health.user.components.presentation.component.History as CHistory
import feo.health.user.components.presentation.component.Favourites as CFavourites
import feo.health.user.components.presentation.component.Profile as CProfile

sealed class UserState {

    sealed class History : UserState() {
        data class Default(val history: Map<String, List<UCatalogItem>>) : History()
        data object Loading : History(), ILoading {
            @Composable
            override fun LoadingScreen(vararg params: Any) =
                CHistory.LoadingScreen()
        }
    }

    sealed class Profile : UserState() {
        data object Default : Profile() {
            var user: User? = null
        }

        data object Loading : Profile()
    }

    sealed class Favourites : UserState() {
        data class Default(val favourites: Map<String, List<UCatalogItem>>) : Favourites()
        data object Loading : Favourites(), ILoading {
            @Composable
            override fun LoadingScreen(vararg params: Any) =
                CFavourites.LoadingScreen()
        }
    }
}