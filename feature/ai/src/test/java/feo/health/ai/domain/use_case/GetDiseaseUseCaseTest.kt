package feo.health.ai.domain.use_case

import feo.health.ai.domain.model.request.FeatureDiseaseRequestDomain
import feo.health.ai.domain.model.response.FeatureDiseaseResponseDomain
import feo.health.ai.domain.repository.IAiRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetDiseaseUseCaseTest {

    private val aiRepository: IAiRepository = mockk()
    private val getDiseaseUseCase = GetDiseaseUseCase(aiRepository)

    @Test
    fun `invoke should call getDisease on repository and return result`() = runTest {
        // Arrange
        val request = FeatureDiseaseRequestDomain(
            symptoms = listOf("Fever", "Cough")
        )
        val expectedResponse = FeatureDiseaseResponseDomain(
            possibleDiseases = mapOf("Flu" to 0.8),
            doctors = listOf("Therapist"),
            generalResponse = "Rest and drink fluids"
        )
        coEvery { aiRepository.getDisease(request) } returns expectedResponse

        // Act
        val actualResponse = getDiseaseUseCase(request)

        // Assert
        assertEquals(expectedResponse, actualResponse)
        coVerify(exactly = 1) { aiRepository.getDisease(request) }
    }
}
