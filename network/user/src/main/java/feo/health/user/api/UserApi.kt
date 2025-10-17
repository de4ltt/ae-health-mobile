package feo.health.user.api

import feo.health.network.endpoints.IApiEndpoints
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
import io.ktor.client.request.setBody
import javax.inject.Inject

class UserApi @Inject constructor(
    private val httpClient: HttpClient,
    private val userEndpoints: IApiEndpoints.User
) : IUserApi {

    override suspend fun getFavourites(): NetworkResult<Map<String, List<CatalogItemResponse>>> =
        RequestHandler.handle {
            httpClient.get(userEndpoints.FAVOURITES).body()
        }

    override suspend fun addFavourite(catalogItemRequest: CatalogItemRequest): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.post(userEndpoints.FAVOURITES) {
                setBody(catalogItemRequest)
            }
        }

    override suspend fun deleteFavourite(catalogItemRequest: CatalogItemRequest): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.delete(userEndpoints.FAVOURITES) {
                setBody(catalogItemRequest)
            }
        }

    override suspend fun getHistory(): NetworkResult<Map<String, List<CatalogItemResponse>>> =
        RequestHandler.handle {
            httpClient.post(userEndpoints.HISTORY).body()
        }

    override suspend fun deleteHistoryItem(catalogItemRequest: CatalogItemRequest): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.post(userEndpoints.HISTORY) {
                setBody(catalogItemRequest)
            }
        }

    override suspend fun getUserInfo(): NetworkResult<UserDto> =
        RequestHandler.handle {
            httpClient.get(userEndpoints.USER).body()
        }

    override suspend fun updateUserInfo(userDto: UserDto): NetworkResult<UserDto> =
        RequestHandler.handle {
            httpClient.post(userEndpoints.USER) {
                setBody(userDto)
            }.body()
        }

    override suspend fun deleteUser(): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.delete(userEndpoints.USER)
        }

    override suspend fun changePassword(changePasswordRequest: ChangePasswordRequest): NetworkResult<Unit> =
        RequestHandler.handle {
            httpClient.post(userEndpoints.PASSWORD) {
                setBody(changePasswordRequest)
            }
        }
}