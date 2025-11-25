package feo.health.user.components.data.repository

import feo.health.network.model.mapResult
import feo.health.user.api.IUserApi
import feo.health.user.components.data.mapper.ChangePasswordDomainToChangePasswordRequestMapper.toChangePasswordRequest
import feo.health.user.components.data.mapper.UserDtoToUserDomainMapper.toUserDomain
import feo.health.user.components.data.mapper.UserDtoToUserDomainMapper.toUserDto
import feo.health.user.components.domain.model.ChangePasswordDomain
import feo.health.user.components.domain.model.UserDomain
import feo.health.user.components.domain.repository.IUserRepository
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: IUserApi
) : IUserRepository {
    override suspend fun getUserInfo(): UserDomain =
        userApi.getUserInfo().mapResult { it.toUserDomain() }

    override suspend fun updateUserInfo(user: UserDomain): UserDomain =
        userApi.updateUserInfo(user.toUserDto()).mapResult { it.toUserDomain() }

    override suspend fun deleteUser() =
        userApi.deleteUser().mapResult { it }

    override suspend fun logOut() =
        userApi.logOut().mapResult { it }

    override suspend fun changePassword(changePassword: ChangePasswordDomain) =
        userApi.changePassword(changePassword.toChangePasswordRequest()).mapResult { it }
}