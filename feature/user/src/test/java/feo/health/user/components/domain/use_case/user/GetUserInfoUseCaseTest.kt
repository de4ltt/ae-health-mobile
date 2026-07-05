package feo.health.user.components.domain.use_case.user

import feo.health.user.components.domain.model.UserDomain
import feo.health.user.components.domain.repository.IUserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class GetUserInfoUseCaseTest {

    private val userRepository: IUserRepository = mockk()
    private val getUserInfoUseCase = GetUserInfoUseCase(userRepository)

    @Test
    fun `invoke should call getUserInfo on repository and return result`() = runTest {
        // Arrange
        val expectedUser = UserDomain(
            name = "John Doe",
            email = "john@example.com",
            dateOfBirth = LocalDate.of(1990, 1, 1),
            weightKg = 75.0f,
            height = 180
        )
        coEvery { userRepository.getUserInfo() } returns expectedUser

        // Act
        val actualUser = getUserInfoUseCase()

        // Assert
        assertEquals(expectedUser, actualUser)
        coVerify(exactly = 1) { userRepository.getUserInfo() }
    }
}
