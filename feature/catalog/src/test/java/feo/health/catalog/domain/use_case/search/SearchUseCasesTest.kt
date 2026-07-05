package feo.health.catalog.domain.use_case.search

import feo.health.catalog.domain.model.CoordsDomain
import feo.health.catalog.domain.model.SearchDomain
import feo.health.catalog.domain.repository.ISearchRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchUseCasesTest {

    private val searchRepository: ISearchRepository = mockk()

    private val getCoordsForAddressUseCase = GetCoordsForAddressUseCase(searchRepository)
    private val searchUseCase = SearchUseCase(searchRepository)

    @Test
    fun `GetCoordsForAddressUseCase should call getCoordsForAddress on repository`() = runTest {
        val expected = CoordsDomain(lat = 1.0, lon = 2.0)
        coEvery { searchRepository.getCoordsForAddress("Krasnodar") } returns expected

        val actual = getCoordsForAddressUseCase("Krasnodar")

        assertEquals(expected, actual)
        coVerify(exactly = 1) { searchRepository.getCoordsForAddress("Krasnodar") }
    }

    @Test
    fun `SearchUseCase should call search on repository`() = runTest {
        val expected = SearchDomain(doctors = emptyList(), clinics = emptyList(), services = emptyList())
        coEvery { searchRepository.search("q", true) } returns expected

        val actual = searchUseCase("q", true)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { searchRepository.search("q", true) }
    }
}
