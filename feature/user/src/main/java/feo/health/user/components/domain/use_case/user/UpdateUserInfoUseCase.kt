package feo.health.user.components.domain.use_case.user

import feo.health.user.components.domain.model.UserDomain
import feo.health.user.components.domain.repository.IUserRepository
import javax.inject.Inject

/**
 * Use case for updating the user's profile information.
 *
 * @property userRepository The repository responsible for managing user data.
 */
class UpdateUserInfoUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    /**
     * Executes the use case to update user profile information.
     *
     * @param user The updated user profile domain data.
     * @return The updated user profile data as returned by the repository.
     */
    suspend operator fun invoke(user: UserDomain) = userRepository.updateUserInfo(user)
}