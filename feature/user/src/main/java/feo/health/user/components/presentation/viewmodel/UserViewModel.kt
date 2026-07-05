package feo.health.user.components.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import feo.health.ui.component.HToast.tryWithToast
import feo.health.ui.dispatcher.AppDispatchers
import feo.health.ui.viewmodel.HViewModel
import feo.health.user.components.domain.use_case.util.IFavouriteUseCases
import feo.health.user.components.domain.use_case.util.IHistoryUseCases
import feo.health.user.components.domain.use_case.util.IUserUseCases
import feo.health.user.components.presentation.mapper.AdditionalMapper.toPresentationMap
import feo.health.user.components.presentation.mapper.CatalogItemDomainToUCatalogItemMapper.toDomain as toCatalogItemDomain
import feo.health.user.components.presentation.mapper.ChangePasswordDomainToChangePasswordMapper.toDomain as toChangePasswordDomain
import feo.health.user.components.presentation.mapper.UserToUserDomainMapper.toUser
import feo.health.user.components.presentation.mapper.UserToUserDomainMapper.toDomain as toUserDomain
import feo.health.user.components.presentation.model.ChangePassword
import feo.health.user.components.presentation.model.UCatalogItem
import feo.health.user.components.presentation.model.User
import feo.health.user.components.presentation.viewmodel.companion.UserEvent
import feo.health.user.components.presentation.viewmodel.companion.UserState
import javax.inject.Inject

/**
 * ViewModel for managing user-related flows including profile details, settings, view history, and favourites.
 * Extends [HViewModel] to utilize state management conventions.
 *
 * @property favouriteUseCases Use cases for managing favourite items.
 * @property historyUseCases Use cases for managing viewing history.
 * @property userUseCases Use cases for general user account operations.
 */
class UserViewModel
/**
 * Primary constructor for [UserViewModel] injecting required use case boundaries.
 *
 * @param favouriteUseCases Use cases for managing favourite items.
 * @param historyUseCases Use cases for managing viewing history.
 * @param userUseCases Use cases for general user account operations.
 */
@Inject constructor(
    private val favouriteUseCases: IFavouriteUseCases,
    private val historyUseCases: IHistoryUseCases,
    private val userUseCases: IUserUseCases
) : HViewModel<UserState, UserEvent>(initialState = UserState.Profile.Loading) {

    /**
     * Entry point for incoming UI events. Routes events to their corresponding processors.
     *
     * @param event The [UserEvent] triggered by user interaction.
     */
    override fun onEvent(event: UserEvent) = when (event) {
        UserEvent.OnBack -> onBack()
        UserEvent.Favourites.OnRefresh -> onRefreshFavourite()
        UserEvent.History.OnRefresh -> onRefreshHistory()
        is UserEvent.Profile.OnChangePassword -> onChangePassword(event.changePassword)
        UserEvent.Profile.OnDeleteUser -> onDeleteUser()
        is UserEvent.Profile.OnUpdateUserData -> onUpdateUserData(event.user)
        is UserEvent.Favourites.OnDeleteFavourite -> onDeleteFavourite(event.item)
        is UserEvent.History.OnDeleteHistory -> onDeleteHistory(event.item)
        is UserEvent.OnItemDetails -> onItemDetails(event.item)
        UserEvent.Profile.OnLogOut -> onLogOut()
        UserEvent.Profile.OnRefresh -> onRefreshProfile()
    }

    /**
     * Fetches current user profile information and updates the state.
     *
     * @return The launched coroutine [kotlinx.coroutines.Job].
     */
    private fun onRefreshProfile() = viewModelScope.tryWithToast {
        val user = userUseCases.getUserInfoUseCase().toUser()
        val defaultState = UserState.Profile.Default(user)
        updateScreenState(defaultState)
        super.initialState = defaultState
    }

    /**
     * Initiates the account log out sequence.
     *
     * @return The launched coroutine [kotlinx.coroutines.Job].
     */
    private fun onLogOut() = viewModelScope.tryWithToast(
        successMessageRequired = true,
        onError = { revertScreenState() }
    ) {
        userUseCases.logOutUseCase()
    }

    /**
     * Submits updated profile information for the current user.
     *
     * @param user The [User] model containing updated details.
     * @return The launched coroutine [kotlinx.coroutines.Job].
     */
    private fun onUpdateUserData(user: User) = viewModelScope.tryWithToast(
        successMessageRequired = true,
        onError = { revertScreenState() }
    ) {
        updateScreenState(UserState.Profile.Loading)
        val newUser = userUseCases.updateUserInfoUseCase(user.toUserDomain()).toUser()
        updateScreenState(UserState.Profile.Default(newUser))
    }

    /**
     * Triggers the user account deletion flow.
     *
     * @return The launched coroutine [kotlinx.coroutines.Job].
     */
    private fun onDeleteUser() = viewModelScope.tryWithToast(
        onError = { revertScreenState() }
    ) {
        updateScreenState(UserState.Profile.Loading)
        userUseCases.deleteUserUseCase()
    }

    /**
     * Changes the user's password.
     *
     * @param changePassword Model specifying the old and new passwords.
     * @return The launched coroutine [kotlinx.coroutines.Job].
     */
    private fun onChangePassword(changePassword: ChangePassword) = viewModelScope.tryWithToast(
        successMessageRequired = true,
        onError = { revertScreenState() }
    ) {
        updateScreenState(UserState.Profile.Loading)
        userUseCases.changePasswordUseCase(changePassword.toChangePasswordDomain())
        val currentUser = (screenState.value as? UserState.Profile.Default)?.user
        updateScreenState(UserState.Profile.Default(currentUser))
    }

    /**
     * Removes an item from the user's favourites list.
     *
     * @param item The [UCatalogItem] to delete.
     * @return The launched coroutine [kotlinx.coroutines.Job].
     */
    private fun onDeleteFavourite(item: UCatalogItem) = viewModelScope.tryWithToast(
        successMessageRequired = true,
        onError = { revertScreenState() }
    ) {
        updateScreenState(UserState.Favourites.Loading)
        favouriteUseCases.deleteFavouriteUseCase(item.toCatalogItemDomain())
        onRefreshFavourite()
    }

    /**
     * Removes an item from the user's viewing history.
     *
     * @param item The [UCatalogItem] to delete.
     * @return The launched coroutine [kotlinx.coroutines.Job].
     */
    private fun onDeleteHistory(item: UCatalogItem) = viewModelScope.tryWithToast(
        successMessageRequired = true,
        onError = { revertScreenState() }
    ) {
        updateScreenState(UserState.History.Loading)
        historyUseCases.deleteHistoryItemUseCase(item.toCatalogItemDomain())
        onRefreshHistory()
    }

    /**
     * Refreshes the user's viewing history items.
     *
     * @return The launched coroutine [kotlinx.coroutines.Job].
     */
    private fun onRefreshHistory() = viewModelScope.tryWithToast(
        onError = { revertScreenState() }
    ) {
        updateScreenState(UserState.History.Loading)
        val result = historyUseCases.getHistoryUseCase().toPresentationMap()
        pushScreenState(UserState.History.Default(result))
    }

    /**
     * Handles transitions to detail view for a specific catalog item.
     *
     * @param item The [UCatalogItem] to view.
     * @return The launched coroutine [kotlinx.coroutines.Job].
     */
    private fun onItemDetails(item: UCatalogItem) = viewModelScope.tryWithToast(
        dispatcher = AppDispatchers.main
    ) {
    }

    /**
     * Refreshes the user's favourites list.
     *
     * @return The launched coroutine [kotlinx.coroutines.Job].
     */
    private fun onRefreshFavourite() = viewModelScope.tryWithToast(
        onError = { revertScreenState() }
    ) {
        updateScreenState(UserState.Favourites.Loading)
        val result = favouriteUseCases.getFavouritesUseCase().toPresentationMap()
        pushScreenState(UserState.Favourites.Default(result))
    }
}