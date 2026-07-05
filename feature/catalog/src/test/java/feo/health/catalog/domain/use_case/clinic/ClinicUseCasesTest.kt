package feo.health.catalog.domain.use_case.clinic

import feo.health.catalog.domain.model.ClinicDomain
import feo.health.catalog.domain.model.DoctorDomain
import feo.health.catalog.domain.repository.IClinicRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ClinicUseCasesTest {

    private val clinicRepository: IClinicRepository = mockk()

    private val getClinicsUseCase = GetClinicsUseCase(clinicRepository)
    private val getClinicInfoUseCase = GetClinicInfoUseCase(clinicRepository)
    private val getClinicsByTypeUseCase = GetClinicsByTypeUseCase(clinicRepository)
    private val getClinicDoctorsUseCase = GetClinicDoctorsUseCase(clinicRepository)

    private val sampleClinic = ClinicDomain(
        name = "Clinic A",
        link = "/a",
        address = "123 St",
        phoneNumber = "123",
        imageUri = null,
        itemType = "clinic",
        reviews = null
    )

    private val sampleDoctor = DoctorDomain(
        name = "Dr. Smith",
        link = "/smith",
        specialities = null,
        experience = 10,
        imageUri = null,
        rating = 4.5,
        itemType = "doctor",
        reviews = null
    )

    @Test
    fun `GetClinicsUseCase should call getClinics on repository`() = runTest {
        val expected = listOf(sampleClinic)
        coEvery { clinicRepository.getClinics("test", true) } returns expected

        val actual = getClinicsUseCase("test", true)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { clinicRepository.getClinics("test", true) }
    }

    @Test
    fun `GetClinicInfoUseCase should call getClinicInfo on repository`() = runTest {
        val expected = sampleClinic
        coEvery { clinicRepository.getClinicInfo("/a", false) } returns expected

        val actual = getClinicInfoUseCase("/a", false)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { clinicRepository.getClinicInfo("/a", false) }
    }

    @Test
    fun `GetClinicsByTypeUseCase should call getClinicsByType on repository`() = runTest {
        val expected = listOf(sampleClinic)
        coEvery { clinicRepository.getClinicsByType("/type") } returns expected

        val actual = getClinicsByTypeUseCase("/type")

        assertEquals(expected, actual)
        coVerify(exactly = 1) { clinicRepository.getClinicsByType("/type") }
    }

    @Test
    fun `GetClinicDoctorsUseCase should call getClinicDoctors on repository`() = runTest {
        val expected = listOf(sampleDoctor)
        coEvery { clinicRepository.getClinicDoctors("/a") } returns expected

        val actual = getClinicDoctorsUseCase("/a")

        assertEquals(expected, actual)
        coVerify(exactly = 1) { clinicRepository.getClinicDoctors("/a") }
    }
}
