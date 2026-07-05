package feo.health.user.api

import feo.health.network.model.NetworkResult
import feo.health.user.dto.common.UserDto
import feo.health.user.dto.request.CatalogItemRequest
import feo.health.user.dto.request.ChangePasswordRequest
import feo.health.user.dto.response.CatalogItemResponse

/**
 * API contract for fetching user account profiles, history feeds, and bookmark entries.
 */
interface IUserApi {
    /**
     * Fetch list of user's bookmarked favourites.
     *
     * @return [NetworkResult] wrapping map segment of category strings to matching bookmarked item responses.
     */
    suspend fun getFavourites(): NetworkResult<Map<String, List<CatalogItemResponse>>>

    /**
     * Add catalog entry to user's bookmarked list.
     *
     * @param catalogItemRequest The catalog item details.
     * @return [NetworkResult] wrapping task completion status.
     */
    suspend fun addFavourite(catalogItemRequest: CatalogItemRequest): NetworkResult<Unit>

    /**
     * Delete catalog entry from user's bookmarked list.
     *
     * @param catalogItemRequest The catalog item details.
     * @return [NetworkResult] wrapping task completion status.
     */
    suspend fun deleteFavourite(catalogItemRequest: CatalogItemRequest): NetworkResult<Unit>

    /**
     * Fetch user's navigation history timeline logs.
     *
     * @return [NetworkResult] wrapping map segment of date strings to matching logged history responses.
     */
    suspend fun getHistory(): NetworkResult<Map<String, List<CatalogItemResponse>>>

    /**
     * Delete single history timeline item entry.
     *
     * @param catalogItemRequest Target catalog item details.
     * @return [NetworkResult] wrapping task completion status.
     */
    suspend fun deleteHistoryItem(catalogItemRequest: CatalogItemRequest): NetworkResult<Unit>

    /**
     * Fetch user account profile parameter settings metrics.
     *
     * @return [NetworkResult] wrapping current account specs.
     */
    suspend fun getUserInfo(): NetworkResult<UserDto>

    /**
     * Update user account profile parameters settings details.
     *
     * @param userDto Concrete update specs parameters.
     * @return [NetworkResult] wrapping updated user profile.
     */
    suspend fun updateUserInfo(userDto: UserDto): NetworkResult<UserDto>

    /**
     * Delete user account profile.
     *
     * @return [NetworkResult] wrapping task completion status.
     */
    suspend fun deleteUser(): NetworkResult<Unit>

    /**
     * Invalidate user session and log out.
     *
     * @return [NetworkResult] wrapping task completion status.
     */
    suspend fun logOut(): NetworkResult<Unit>

    /**
     * Update user account credentials password.
     *
     * @param changePasswordRequest Request payload with old and new password strings.
     * @return [NetworkResult] wrapping task completion status.
     */
    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest): NetworkResult<Unit>
}