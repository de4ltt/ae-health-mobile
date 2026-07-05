package feo.health.user.components.domain.repository

import feo.health.user.components.domain.model.ChangePasswordDomain
import feo.health.user.components.domain.model.UserDomain

/**
 * Repository interface for managing user authentication and profile data.
 */
interface IUserRepository {
    /**
     * Retrieves the current user's profile information.
     *
     * @return The user profile data as a [UserDomain].
     */
    suspend fun getUserInfo(): UserDomain

    /**
     * Updates the user's profile information.
     *
     * @param user The updated user profile data.
     * @return The newly updated user profile data returned from the source.
     */
    suspend fun updateUserInfo(user: UserDomain): UserDomain

    /**
     * Deletes the user account permanently.
     */
    suspend fun deleteUser()

    /**
     * Logs the current user out of the application, clearing sessions/tokens.
     */
    suspend fun logOut()

    /**
     * Updates the current user's password.
     *
     * @param changePassword The request containing old and new passwords.
     */
    suspend fun changePassword(changePassword: ChangePasswordDomain)
}