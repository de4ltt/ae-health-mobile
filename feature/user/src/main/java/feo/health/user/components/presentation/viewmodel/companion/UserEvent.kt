package feo.health.user.components.presentation.viewmodel.companion

import feo.health.user.components.presentation.model.ChangePassword
import feo.health.user.components.presentation.model.UCatalogItem
import feo.health.user.components.presentation.model.User

sealed interface UserEvent {

    sealed interface Profile: UserEvent {
        data object OnRefresh: Profile
        data object OnDeleteUser: Profile
        data object OnLogOut: Profile
        data class OnUpdateUserData(val user: User): Profile
        data class OnChangePassword(val changePassword: ChangePassword): Profile
    }

    sealed interface History: UserEvent {
        data class OnDeleteHistory(val item: UCatalogItem): History
        data object OnRefresh: History
    }

    sealed interface Favourites: UserEvent {
        data class OnDeleteFavourite(val item: UCatalogItem): Favourites
        data object OnRefresh: Favourites
    }

    data class OnItemDetails(val item: UCatalogItem): UserEvent
    data object OnBack: UserEvent
}