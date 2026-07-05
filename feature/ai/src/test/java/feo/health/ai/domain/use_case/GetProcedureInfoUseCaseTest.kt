package feo.health.ai.domain.use_case

import feo.health.ai.domain.model.request.FeatureProcedureRequestDomain
import feo.health.ai.domain.model.response.FeatureProcedureResponseDomain
import feo.health.ai.domain.repository.IAiRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetProcedureInfoUseCaseTest {

    private val aiRepository: IAiRepository = mockk()
    private val getProcedureInfoUseCase = GetProcedureInfoUseCase(aiRepository)

    @Test
    fun `invoke should call getProcedureInfo on repository and return result`() = runTest {
        // Arrange
        val request = FeatureProcedureRequestDomain(
            serviceName = "MRI"
        )
        val expectedResponse = FeatureProcedureResponseDomain(
            name = "MRI",
            description = "Magnetic resonance imaging",
            contradictions = listOf("Pacemaker"),
            indications = listOf("Brain injury")
        )
        coEvery { aiRepository.getProcedureInfo(request) } returns expectedResponse

        // Act
        val actualResponse = getProcedureInfoUseCase(request)

        // Assert
        assertEquals(expectedResponse, actualResponse)
        coVerify(exactly = 1) { aiRepository.getProcedureInfo(request) }
    }
}
