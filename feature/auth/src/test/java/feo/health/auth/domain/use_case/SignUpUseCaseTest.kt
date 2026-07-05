package feo.health.auth.domain.use_case

import feo.health.auth.domain.model.SignUpDomain
import feo.health.auth.domain.repository.IAuthRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.runs
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

import java.time.LocalDate

class SignUpUseCaseTest {

    private val authRepository: IAuthRepository = mockk()
    private val signUpUseCase = SignUpUseCase(authRepository)

    @Test
    fun `invoke should call signUp on repository`() = runTest {
        // Arrange
        val signUpDomain = SignUpDomain(
            email = "test@example.com",
            password = "password123",
            name = "Test User",
            dateOfBirth = LocalDate.of(1990, 1, 1)
        )
        coEvery { authRepository.signUp(signUpDomain) } just runs

        // Act
        signUpUseCase(signUpDomain)

        // Assert
        coVerify(exactly = 1) { authRepository.signUp(signUpDomain) }
    }
}
