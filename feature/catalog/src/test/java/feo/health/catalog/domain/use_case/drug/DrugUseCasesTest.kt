package feo.health.catalog.domain.use_case.drug

import feo.health.catalog.domain.model.DrugDomain
import feo.health.catalog.domain.repository.IDrugRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DrugUseCasesTest {

    private val drugRepository: IDrugRepository = mockk()

    private val getDrugInfoUseCase = GetDrugInfoUseCase(drugRepository)
    private val getDrugsUseCase = GetDrugsUseCase(drugRepository)

    private val sampleDrug = DrugDomain(
        name = "Aspirin",
        latinName = "Acidum acetylsalicylicum",
        link = "/aspirin",
        imageUri = null,
        effectiveness = 4.8,
        rating = 4.7,
        priceQuality = 4.5,
        sideEffects = 1.2,
        reviewsCount = 10,
        forms = emptyList(),
        instructionSections = emptyList()
    )

    @Test
    fun `GetDrugInfoUseCase should call getDrugInfo on repository`() = runTest {
        val expected = sampleDrug
        coEvery { drugRepository.getDrugInfo("/aspirin") } returns expected

        val actual = getDrugInfoUseCase("/aspirin")

        assertEquals(expected, actual)
        coVerify(exactly = 1) { drugRepository.getDrugInfo("/aspirin") }
    }

    @Test
    fun `GetDrugsUseCase should call getDrugs on repository`() = runTest {
        val expected = listOf(sampleDrug)
        coEvery { drugRepository.getDrugs("aspirin") } returns expected

        val actual = getDrugsUseCase("aspirin")

        assertEquals(expected, actual)
        coVerify(exactly = 1) { drugRepository.getDrugs("aspirin") }
    }
}
