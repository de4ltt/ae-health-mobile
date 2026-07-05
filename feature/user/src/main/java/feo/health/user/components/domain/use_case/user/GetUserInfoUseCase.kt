package feo.health.user.components.domain.use_case.user

import feo.health.user.components.domain.repository.IUserRepository
import javax.inject.Inject

/**
 * Use case for retrieving the current user's profile information.
 *
 * @property userRepository The repository responsible for managing user data.
 */
class GetUserInfoUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    /**
     * Executes the use case to retrieve the user's profile info.
     *
     * @return The user profile data as a [UserDomain].
     */
    suspend operator fun invoke() = userRepository.getUserInfo()
}