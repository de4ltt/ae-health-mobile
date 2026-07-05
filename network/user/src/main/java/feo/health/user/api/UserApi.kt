package feo.health.user.api

import feo.health.network.datastore.HDataStore
import feo.health.network.endpoints.ApiEndpoints
import feo.health.network.model.NetworkResult
import feo.health.network.util.RequestHandler
import feo.health.user.dto.common.UserDto
import feo.health.user.dto.request.CatalogItemRequest
import feo.health.user.dto.request.ChangePasswordRequest
import feo.health.user.dto.response.CatalogItemResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import javax.inject.Inject

/**
 * API implementation managing user bookmarks, history tracking, password, and account settings update.
 *
 * @property httpClient Network HTTP client provider.
 * @property dataStore Local store containing cached session credentials.
 */
class UserApi @Inject constructor(
    private val httpClient: HttpClient,
    private val dataStore: HDataStore
) : IUserApi {

    /**
     * Queries user's bookmarked favourites.
     *
     * @return [NetworkResult] wrapping map of category identifier strings to matched bookmarked listings.
     */
    override suspend fun getFavourites(): NetworkResult<Map<String, List<CatalogItemResponse>>> =
        RequestHandler.handle {
            httpClient.get(ApiEndpoints.User.GET_FAVOURITES).body()
        }

    /**
     * Bookmarks specified catalog item under user preferences.
     *
     * @param catalogItemRequest The catalog item details parameters.
     * @return [NetworkResult] wrapping add action status.
     */
    override suspend fun addFavourite(catalogItemRequest: CatalogItemRequest): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.post(ApiEndpoints.User.POST_FAVOURITE) {
                setBody(catalogItemRequest)
            }
        }

    /**
     * Unbookmarks specified catalog item from user preferences list.
     *
     * @param catalogItemRequest Target catalog item details parameters.
     * @return [NetworkResult] wrapping delete action status.
     */
    override suspend fun deleteFavourite(catalogItemRequest: CatalogItemRequest): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.delete(ApiEndpoints.User.DELETE_FAVOURITE) {
                setBody(catalogItemRequest)
            }
        }

    /**
     * Queries user's interaction logs history.
     *
     * @return [NetworkResult] wrapping map of date label strings to matching history item responses.
     */
    override suspend fun getHistory(): NetworkResult<Map<String, List<CatalogItemResponse>>> =
        RequestHandler.handle {
            httpClient.get(ApiEndpoints.User.GET_HISTORY).body()
        }

    /**
     * Deletes single history interaction log from timeline stream.
     *
     * @param catalogItemRequest Target catalog item details parameters.
     * @return [NetworkResult] wrapping delete action status.
     */
    override suspend fun deleteHistoryItem(catalogItemRequest: CatalogItemRequest): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.delete(ApiEndpoints.User.DELETE_HISTORY) {
                setBody(catalogItemRequest)
            }
        }

    /**
     * Queries user account profile metrics parameters.
     *
     * @return [NetworkResult] wrapping current account specs.
     */
    override suspend fun getUserInfo(): NetworkResult<UserDto> =
        RequestHandler.handle {
            httpClient.get(ApiEndpoints.User.GET_USER).body()
        }

    /**
     * Updates user account profile variables settings.
     *
     * @param userDto Update parameter target specs.
     * @return [NetworkResult] wrapping updated user profile.
     */
    override suspend fun updateUserInfo(userDto: UserDto): NetworkResult<UserDto> =
        RequestHandler.handle {
            httpClient.put(ApiEndpoints.User.PUT_USER) {
                setBody(userDto)
            }.body()
        }

    /**
     * Deletes user account data profile remotely, and clears local credentials.
     *
     * @return [NetworkResult] wrapping task status.
     */
    override suspend fun deleteUser(): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.delete(ApiEndpoints.User.DELETE_USER)
            dataStore.clear()
        }

    /**
     * Logs out user session locally by clearing credential tokens store.
     *
     * @return [NetworkResult] wrapping task status.
     */
    override suspend fun logOut(): NetworkResult<Unit> =
        RequestHandler.handle {
            dataStore.clear()
        }

    /**
     * Triggers updated credentials password post request validation verification.
     *
     * @param changePasswordRequest Verification credentials payload.
     * @return [NetworkResult] wrapping task status.
     */
    override suspend fun changePassword(changePasswordRequest: ChangePasswordRequest): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.post(ApiEndpoints.User.POST_CHANGE_PASSWORD) {
                setBody(changePasswordRequest)
            }
        }
}