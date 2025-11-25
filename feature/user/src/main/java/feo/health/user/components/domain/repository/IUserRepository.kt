package feo.health.user.components.domain.repository

import feo.health.network.model.NetworkResult
import feo.health.user.components.domain.model.ChangePasswordDomain
import feo.health.user.components.domain.model.UserDomain

interface IUserRepository {
    suspend fun getUserInfo(): UserDomain
    suspend fun updateUserInfo(user: UserDomain): UserDomain
    suspend fun deleteUser()
    suspend fun logOut()
    suspend fun changePassword(changePassword: ChangePasswordDomain)
}