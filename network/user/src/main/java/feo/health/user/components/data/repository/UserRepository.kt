package feo.health.user.components.data.repository

import feo.health.network.model.mapResult
import feo.health.user.api.IUserApi
import feo.health.user.components.data.mapper.ChangePasswordDomainToChangePasswordRequestMapper.toRequest as toChangePasswordRequest
import feo.health.user.components.data.mapper.UserDtoToUserDomainMapper.toDomain as toUserDomain
import feo.health.user.components.data.mapper.UserDtoToUserDomainMapper.toDto as toUserDto
import feo.health.user.components.domain.model.ChangePasswordDomain
import feo.health.user.components.domain.model.UserDomain
import feo.health.user.components.domain.repository.IUserRepository
import javax.inject.Inject

/**
 * Repository interface implementation managing user account actions, logs status, updates profile settings, and updates passwords.
 *
 * @property userApi Remote user action endpoints API client.
 */
class UserRepository @Inject constructor(
    private val userApi: IUserApi
) : IUserRepository {

    /**
     * Queries current user account profile metrics parameters.
     *
     * @return Domain [UserDomain] user profile entity.
     */
    override suspend fun getUserInfo(): UserDomain =
        userApi.getUserInfo().mapResult { it.toUserDomain() }

    /**
     * Submits updated user account profile parameters configurations.
     *
     * @param user Domain updated target specs settings parameters.
     * @return Domain [UserDomain] user profile updated entity configurations.
     */
    override suspend fun updateUserInfo(user: UserDomain): UserDomain =
        userApi.updateUserInfo(user.toUserDto()).mapResult { it.toUserDomain() }

    /**
     * Submits user profile delete command request to remote auth endpoints.
     */
    override suspend fun deleteUser() =
        userApi.deleteUser().mapResult { it }

    /**
     * Submits invalidate session logout command request.
     */
    override suspend fun logOut() =
        userApi.logOut().mapResult { it }

    /**
     * Submits updated user security verification password validation credentials.
     *
     * @param changePassword Verification credentials domain container.
     */
    override suspend fun changePassword(changePassword: ChangePasswordDomain) =
        userApi.changePassword(changePassword.toChangePasswordRequest()).mapResult { it }
}