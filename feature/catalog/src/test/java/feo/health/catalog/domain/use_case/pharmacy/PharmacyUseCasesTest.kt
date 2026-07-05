package feo.health.catalog.domain.use_case.pharmacy

import feo.health.catalog.domain.model.PharmacyDomain
import feo.health.catalog.domain.repository.IPharmacyRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.runs
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PharmacyUseCasesTest {

    private val pharmacyRepository: IPharmacyRepository = mockk()

    private val getPharmaciesUseCase = GetPharmaciesUseCase(pharmacyRepository)
    private val getPharmacyByIdUseCase = GetPharmacyByIdUseCase(pharmacyRepository)
    private val visitPharmacyUseCase = VisitPharmacyUseCase(pharmacyRepository)

    @Test
    fun `GetPharmaciesUseCase should call getPharmacies on repository`() = runTest {
        val expected = listOf(PharmacyDomain(name = "Pharmacy A", phoneNumber = "123", website = "web", address = "123 St", openingHours = listOf("9:00 - 18:00")))
        coEvery { pharmacyRepository.getPharmacies(1.0, 2.0, 3) } returns expected

        val actual = getPharmaciesUseCase(1.0, 2.0, 3)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { pharmacyRepository.getPharmacies(1.0, 2.0, 3) }
    }

    @Test
    fun `GetPharmacyByIdUseCase should call getPharmacyById on repository`() = runTest {
        val expected = PharmacyDomain(name = "Pharmacy A", phoneNumber = "123", website = "web", address = "123 St", openingHours = listOf("9:00 - 18:00"))
        coEvery { pharmacyRepository.getPharmacyById(123L) } returns expected

        val actual = getPharmacyByIdUseCase(123L)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { pharmacyRepository.getPharmacyById(123L) }
    }

    @Test
    fun `VisitPharmacyUseCase should call visitPharmacy on repository`() = runTest {
        val pharmacy = PharmacyDomain(name = "Pharmacy A", phoneNumber = "123", website = "web", address = "123 St", openingHours = listOf("9:00 - 18:00"))
        coEvery { pharmacyRepository.visitPharmacy(pharmacy) } just runs

        visitPharmacyUseCase(pharmacy)

        coVerify(exactly = 1) { pharmacyRepository.visitPharmacy(pharmacy) }
    }
}
