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

class UserApi @Inject constructor(
    private val httpClient: HttpClient,
    private val dataStore: HDataStore
) : IUserApi {

    override suspend fun getFavourites(): NetworkResult<Map<String, List<CatalogItemResponse>>> =
        RequestHandler.handle {
            httpClient.get(ApiEndpoints.User.GET_FAVOURITES).body()
        }

    override suspend fun addFavourite(catalogItemRequest: CatalogItemRequest): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.post(ApiEndpoints.User.POST_FAVOURITE) {
                setBody(catalogItemRequest)
            }
        }

    override suspend fun deleteFavourite(catalogItemRequest: CatalogItemRequest): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.delete(ApiEndpoints.User.DELETE_FAVOURITE) {
                setBody(catalogItemRequest)
            }
        }

    override suspend fun getHistory(): NetworkResult<Map<String, List<CatalogItemResponse>>> =
        RequestHandler.handle {
            httpClient.get(ApiEndpoints.User.GET_HISTORY).body()
        }

    override suspend fun deleteHistoryItem(catalogItemRequest: CatalogItemRequest): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.delete(ApiEndpoints.User.DELETE_HISTORY) {
                setBody(catalogItemRequest)
            }
        }

    override suspend fun getUserInfo(): NetworkResult<UserDto> =
        RequestHandler.handle {
            httpClient.get(ApiEndpoints.User.GET_USER).body()
        }

    override suspend fun updateUserInfo(userDto: UserDto): NetworkResult<UserDto> =
        RequestHandler.handle {
            httpClient.put(ApiEndpoints.User.PUT_USER) {
                setBody(userDto)
            }.body()
        }

    override suspend fun deleteUser(): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.delete(ApiEndpoints.User.DELETE_USER)
            dataStore.clear()
        }

    override suspend fun logOut(): NetworkResult<Unit> =
        RequestHandler.handle {
            dataStore.clear()
        }

    override suspend fun changePassword(changePasswordRequest: ChangePasswordRequest): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.post(ApiEndpoints.User.POST_CHANGE_PASSWORD) {
                setBody(changePasswordRequest)
            }
        }
}