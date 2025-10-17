package feo.health.user.api

import feo.health.network.model.NetworkResult
import feo.health.user.dto.request.CatalogItemRequest
import feo.health.user.dto.response.CatalogItemResponse
import feo.health.user.dto.common.UserDto
import feo.health.user.dto.request.ChangePasswordRequest

interface IUserApi {
    suspend fun getFavourites(): NetworkResult<Map<String, List<CatalogItemResponse>>>
    suspend fun addFavourite(catalogItemRequest: CatalogItemRequest): NetworkResult<Unit>
    suspend fun deleteFavourite(catalogItemRequest: CatalogItemRequest): NetworkResult<Unit>

    suspend fun getHistory(): NetworkResult<Map<String, List<CatalogItemResponse>>>
    suspend fun deleteHistoryItem(catalogItemRequest: CatalogItemRequest): NetworkResult<Unit>

    suspend fun getUserInfo(): NetworkResult<UserDto>
    suspend fun updateUserInfo(userDto: UserDto): NetworkResult<UserDto>
    suspend fun deleteUser(): NetworkResult<Unit>

    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest): NetworkResult<Unit>
}