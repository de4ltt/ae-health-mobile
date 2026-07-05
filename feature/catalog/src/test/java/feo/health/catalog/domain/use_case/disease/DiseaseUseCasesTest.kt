package feo.health.catalog.domain.use_case.disease

import feo.health.catalog.domain.model.DiseaseDomain
import feo.health.catalog.domain.repository.IDiseaseRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DiseaseUseCasesTest {

    private val diseaseRepository: IDiseaseRepository = mockk()

    private val getDiseasesUseCase = GetDiseasesUseCase(diseaseRepository)
    private val getDiseaseInfoUseCase = GetDiseaseInfoUseCase(diseaseRepository)

    @Test
    fun `GetDiseasesUseCase should call getDiseases on repository`() = runTest {
        val expected = listOf(DiseaseDomain(name = "Flu", link = "/flu"))
        coEvery { diseaseRepository.getDiseases("flu") } returns expected

        val actual = getDiseasesUseCase("flu")

        assertEquals(expected, actual)
        coVerify(exactly = 1) { diseaseRepository.getDiseases("flu") }
    }

    @Test
    fun `GetDiseaseInfoUseCase should call getDiseaseInfo on repository`() = runTest {
        val expected = "Flu description and treatments"
        coEvery { diseaseRepository.getDiseaseInfo("/flu") } returns expected

        val actual = getDiseaseInfoUseCase("/flu")

        assertEquals(expected, actual)
        coVerify(exactly = 1) { diseaseRepository.getDiseaseInfo("/flu") }
    }
}
