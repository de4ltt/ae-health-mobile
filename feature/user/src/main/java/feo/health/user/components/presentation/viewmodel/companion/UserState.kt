package feo.health.user.components.presentation.viewmodel.companion

import androidx.compose.runtime.Composable
import feo.health.ui.util.ILoading
import feo.health.user.components.presentation.model.UCatalogItem
import feo.health.user.components.presentation.model.User
import feo.health.user.components.presentation.component.Favourites as CFavourites
import feo.health.user.components.presentation.component.History as CHistory

/**
 * Sealed class representing all possible presentation states of the User feature.
 */
sealed class UserState {

    /**
     * States representing the viewing History screen flow.
     */
    sealed class History : UserState() {
        /**
         * State representing the loaded view history screen with list data.
         *
         * @property history Map grouping history items by category or date.
         */
        data class Default(val history: Map<String, List<UCatalogItem>>) : History()

        /**
         * State representing the loading phase of history items.
         * Implements [ILoading] to provide a dedicated placeholder screen.
         */
        data object Loading : History(), ILoading {
            /**
             * Renders the loading/placeholder screen for history items.
             *
             * @param params Arguments passed for rendering.
             */
            @Composable
            override fun LoadingScreen(vararg params: Any) =
                CHistory.LoadingScreen()
        }
    }

    /**
     * States representing the general user Profile screen flow.
     */
    sealed class Profile : UserState() {
        /**
         * State representing the loaded user profile screen with user details.
         *
         * @property user The user profile information, or null if not yet loaded.
         */
        data class Default(val user: User? = null) : Profile()

        /**
         * State representing the loading phase of user profile details.
         */
        data object Loading : Profile()
    }

    /**
     * States representing the Favourites screen flow.
     */
    sealed class Favourites : UserState() {
        /**
         * State representing the loaded favourites list screen.
         *
         * @property favourites Map grouping favourite items by category.
         */
        data class Default(val favourites: Map<String, List<UCatalogItem>>) : Favourites()

        /**
         * State representing the loading phase of favourites list.
         * Implements [ILoading] to provide a dedicated placeholder screen.
         */
        data object Loading : Favourites(), ILoading {
            /**
             * Renders the loading/placeholder screen for favourites items.
             *
             * @param params Arguments passed for rendering.
             */
            @Composable
            override fun LoadingScreen(vararg params: Any) =
                CFavourites.LoadingScreen()
        }
    }
}