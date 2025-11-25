package feo.health.user.components.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import feo.health.ui.component.HToast.tryWithToast
import feo.health.ui.viewmodel.HViewModel
import feo.health.user.components.domain.use_case.util.IFavouriteUseCases
import feo.health.user.components.domain.use_case.util.IHistoryUseCases
import feo.health.user.components.domain.use_case.util.IUserUseCases
import feo.health.user.components.presentation.mapper.AdditionalMapper.toPresentationMap
import feo.health.user.components.presentation.mapper.CatalogItemDomainToUCatalogItemMapper.toCatalogItemDomain
import feo.health.user.components.presentation.mapper.ChangePasswordDomainToChangePasswordMapper.toChangePasswordDomain
import feo.health.user.components.presentation.mapper.UserToUserDomainMapper.toUser
import feo.health.user.components.presentation.mapper.UserToUserDomainMapper.toUserDomain
import feo.health.user.components.presentation.model.ChangePassword
import feo.health.user.components.presentation.model.UCatalogItem
import feo.health.user.components.presentation.model.User
import feo.health.user.components.presentation.viewmodel.companion.UserEvent
import feo.health.user.components.presentation.viewmodel.companion.UserState
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val favouriteUseCases: IFavouriteUseCases,
    private val historyUseCases: IHistoryUseCases,
    private val userUseCases: IUserUseCases
) : HViewModel<UserState, UserEvent>(initialState = UserState.Profile.Loading) {

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

    private fun onRefreshProfile() = viewModelScope.tryWithToast {
        UserState.Profile.Default.user = userUseCases.getUserInfoUseCase().toUser()
        updateScreenState(UserState.Profile.Default)
        super.initialState = UserState.Profile.Default
    }

    private fun onLogOut() = viewModelScope.tryWithToast(
        successMessageRequired = true,
        onError = { revertScreenState() }
    ) {
        userUseCases.logOutUseCase()
    }

    private fun onUpdateUserData(user: User) = viewModelScope.tryWithToast(
        successMessageRequired = true,
        onError = { revertScreenState() }
    ) {
        updateScreenState(UserState.Profile.Loading)
        val newUser = userUseCases.updateUserInfoUseCase(user.toUserDomain()).toUser()
        UserState.Profile.Default.user = newUser
        updateScreenState(UserState.Profile.Default)
    }

    private fun onDeleteUser() = viewModelScope.tryWithToast(
        onError = { revertScreenState() }
    ) {
        updateScreenState(UserState.Profile.Loading)
        userUseCases.deleteUserUseCase()
    }

    private fun onChangePassword(changePassword: ChangePassword) = viewModelScope.tryWithToast(
        successMessageRequired = true,
        onError = { revertScreenState() }
    ) {
        updateScreenState(UserState.Profile.Loading)
        userUseCases.changePasswordUseCase(changePassword.toChangePasswordDomain())
        updateScreenState(UserState.Profile.Default)
    }

    private fun onDeleteFavourite(item: UCatalogItem) = viewModelScope.tryWithToast(
        successMessageRequired = true,
        onError = { revertScreenState() }
    ) {
        updateScreenState(UserState.Favourites.Loading)
        favouriteUseCases.deleteFavouriteUseCase(item.toCatalogItemDomain())
        onRefreshFavourite()
    }

    private fun onDeleteHistory(item: UCatalogItem) = viewModelScope.tryWithToast(
        successMessageRequired = true,
        onError = { revertScreenState() }
    ) {
        updateScreenState(UserState.History.Loading)
        historyUseCases.deleteHistoryItemUseCase(item.toCatalogItemDomain())
        onRefreshHistory()
    }

    private fun onRefreshHistory() = viewModelScope.tryWithToast(
        onError = { revertScreenState() }
    ) {
        updateScreenState(UserState.History.Loading)
        val result = historyUseCases.getHistoryUseCase().toPresentationMap()
        pushScreenState(UserState.History.Default(result))
    }

    private fun onItemDetails(item: UCatalogItem) = viewModelScope.tryWithToast(
        dispatcher = Dispatchers.Main
    ) {
    }

    private fun onRefreshFavourite() = viewModelScope.tryWithToast(
        onError = { revertScreenState() }
    ) {
        updateScreenState(UserState.Favourites.Loading)
        val result = favouriteUseCases.getFavouritesUseCase().toPresentationMap()
        pushScreenState(UserState.Favourites.Default(result))
    }
}