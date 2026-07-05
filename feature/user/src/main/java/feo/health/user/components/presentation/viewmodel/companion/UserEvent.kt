package feo.health.user.components.presentation.viewmodel.companion

import feo.health.user.components.presentation.model.ChangePassword
import feo.health.user.components.presentation.model.UCatalogItem
import feo.health.user.components.presentation.model.User

/**
 * Sealed interface representing all user interaction events or commands
 * within the user feature.
 */
sealed interface UserEvent {

    /**
     * Sealed interface representing events specific to the user Profile screen.
     */
    sealed interface Profile: UserEvent {
        /**
         * Event triggered to refresh the user profile details.
         */
        data object OnRefresh: Profile

        /**
         * Event triggered to delete the user's account.
         */
        data object OnDeleteUser: Profile

        /**
         * Event triggered to log out of the application.
         */
        data object OnLogOut: Profile

        /**
         * Event triggered to update the user's details.
         *
         * @property user The [User] data containing new values.
         */
        data class OnUpdateUserData(val user: User): Profile

        /**
         * Event triggered to change the user's password.
         *
         * @property changePassword Data containing the old and new passwords.
         */
        data class OnChangePassword(val changePassword: ChangePassword): Profile
    }

    /**
     * Sealed interface representing events specific to the viewing History screen.
     */
    sealed interface History: UserEvent {
        /**
         * Event triggered to delete a viewing history item.
         *
         * @property item The [UCatalogItem] to delete.
         */
        data class OnDeleteHistory(val item: UCatalogItem): History

        /**
         * Event triggered to refresh the history list.
         */
        data object OnRefresh: History
    }

    /**
     * Sealed interface representing events specific to the Favourites screen.
     */
    sealed interface Favourites: UserEvent {
        /**
         * Event triggered to remove an item from favourites.
         *
         * @property item The [UCatalogItem] to remove.
         */
        data class OnDeleteFavourite(val item: UCatalogItem): Favourites

        /**
         * Event triggered to refresh the favourites list.
         */
        data object OnRefresh: Favourites
    }

    /**
     * Event triggered to view the details of a specific catalog item.
     *
     * @property item The [UCatalogItem] whose details should be displayed.
     */
    data class OnItemDetails(val item: UCatalogItem): UserEvent

    /**
     * Event triggered to request navigation backward.
     */
    data object OnBack: UserEvent
}