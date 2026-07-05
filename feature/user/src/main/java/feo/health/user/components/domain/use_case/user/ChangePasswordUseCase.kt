package feo.health.user.components.domain.use_case.user

import feo.health.user.components.domain.model.ChangePasswordDomain
import feo.health.user.components.domain.repository.IUserRepository
import javax.inject.Inject

/**
 * Use case for changing the user's password.
 *
 * @property userRepository The repository responsible for managing user data and session details.
 */
class ChangePasswordUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    /**
     * Executes the use case to update the password.
     *
     * @param changePasswordDomain The domain model containing current and new password data.
     */
    suspend operator fun invoke(
        changePasswordDomain: ChangePasswordDomain
    ) = userRepository.changePassword(changePasswordDomain)
}