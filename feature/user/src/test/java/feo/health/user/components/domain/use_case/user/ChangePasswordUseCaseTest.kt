package feo.health.user.components.domain.use_case.user

import feo.health.user.components.domain.model.ChangePasswordDomain
import feo.health.user.components.domain.repository.IUserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.runs
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ChangePasswordUseCaseTest {

    private val userRepository: IUserRepository = mockk()
    private val changePasswordUseCase = ChangePasswordUseCase(userRepository)

    @Test
    fun `invoke should call changePassword on repository`() = runTest {
        // Arrange
        val changePasswordDomain = ChangePasswordDomain(oldPassword = "old", newPassword = "new")
        coEvery { userRepository.changePassword(changePasswordDomain) } just runs

        // Act
        changePasswordUseCase(changePasswordDomain)

        // Assert
        coVerify(exactly = 1) { userRepository.changePassword(changePasswordDomain) }
    }
}
