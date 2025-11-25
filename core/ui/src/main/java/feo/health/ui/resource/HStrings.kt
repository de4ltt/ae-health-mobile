package feo.health.ui.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import feo.health.ui.R

object HStrings {
    val search
        @Composable get() = stringResource(R.string.search)
    val specialists
        @Composable get() = stringResource(R.string.specialists)
    val watchServices
        @Composable get() = stringResource(R.string.watch_services)
    val nothingFound
        @Composable get() = stringResource(R.string.nothing_found)
    val ai
        @Composable get() = stringResource(R.string.ai)

    val description
        @Composable get() = stringResource(R.string.description)
    val indications
        @Composable get() = stringResource(R.string.indication)
    val contradictions
        @Composable get() = stringResource(R.string.contradictions)

    val possibleDiseases
        @Composable get() = stringResource(R.string.possible_diseases)
    val recommendedDoctors
        @Composable get() = stringResource(R.string.recommended_doctors)
    val possibleMedications
        @Composable get() = stringResource(R.string.possible_medications)

    val startTheSearch
        @Composable get() = stringResource(R.string.start_the_search)
    val phoneNumber
        @Composable get() = stringResource(R.string.phone_number)
    val profile
        @Composable get() = stringResource(R.string.profile)
    val reviews
        @Composable get() = stringResource(R.string.reviews)
    val logOut
        @Composable get() = stringResource(R.string.log_out)
    val deleteAccount
        @Composable get() = stringResource(R.string.delete_account)
    val notSet
        @Composable get() = stringResource(R.string.not_set)
    val changePassword
        @Composable get() = stringResource(R.string.change_password)
    val oldPassword
        @Composable get() = stringResource(R.string.old_password)
    val newPassword
        @Composable get() = stringResource(R.string.new_password)
    val openingHours
        @Composable get() = stringResource(R.string.opening_hours)
    val procede
        @Composable get() = stringResource(R.string.proceed)
    val cancel
        @Composable get() = stringResource(R.string.cancel)
    val cannotGoBack
        @Composable get() = stringResource(R.string.cannot_go_back)
    val hereIsWhatWeFound
        @Composable get() = stringResource(R.string.here_is_what_we_found)
    val website
        @Composable get() = stringResource(R.string.website)
    val address
        @Composable get() = stringResource(R.string.address)
    val searchIsNotAllowed
        @Composable get() = stringResource(R.string.search_is_not_allowed)
    val route
        @Composable get() = stringResource(R.string.route)
    val ok
        @Composable get() = stringResource(R.string.ok)
    val phone
        @Composable get() = stringResource(R.string.phone)
    val appointmentWas
        @Composable get() = stringResource(R.string.appointment_was)
    val history
        @Composable get() = stringResource(R.string.history)
    val favourites
        @Composable get() = stringResource(R.string.favourites)
    val haveSeenRecently
        @Composable get() = stringResource(R.string.have_seen_recently)

    val experience
        @Composable get() = stringResource(R.string.experience)
    val expOneYr
        @Composable get() = stringResource(R.string.exp_one_yr)
    val expManyYrs
        @Composable get() = stringResource(R.string.exp_many_yr)
    val expTwoFourMoreYrs
        @Composable get() = stringResource(R.string.exp_two_four_more_yrs)
    val expTwoFourLessYrs
        @Composable get() = stringResource(R.string.exp_two_four_less_yrs)

    val specialistProfile
        @Composable get() = stringResource(R.string.specialist_profile)
    val type
        @Composable get() = stringResource(R.string.type)
    val sorting
        @Composable get() = stringResource(R.string.sorting)
    val searchRadius
        @Composable get() = stringResource(R.string.search_radius)

    val clinics
        @Composable get() = stringResource(R.string.clinics)
    val doctors
        @Composable get() = stringResource(R.string.doctors)
    val pharmacies
        @Composable get() = stringResource(R.string.pharmacies)
    val services
        @Composable get() = stringResource(R.string.services)

    val title
        @Composable get() = stringResource(R.string.title)
    val rating
        @Composable get() = stringResource(R.string.rating)

    val distance
        @Composable get() = stringResource(R.string.distance)

    val any
        @Composable get() = stringResource(R.string.any)
    val fiveHundredM
        @Composable get() = stringResource(R.string.five_hundred_m)
    val oneKm
        @Composable get() = stringResource(R.string.one_km)
    val twoKm
        @Composable get() = stringResource(R.string.two_km)

    val signIn
        @Composable get() = stringResource(R.string.sign_in)
    val signUp
        @Composable get() = stringResource(R.string.sign_up)

    val addSymptom
        @Composable get() = stringResource(R.string.add_symptom)

    val goodRes
        get() = R.string.good
    val mixedRes
        get() = R.string.mixed
    val badRes
        get() = R.string.bad

    val aiProcedureRes
        get() = R.string.ai_procedure
    val aiDiseaseRes
        get() = R.string.ai_disesase
    val aiSuggestionRes
        get() = R.string.ai_suggestion
    val aiProcedureHintRes
        get() = R.string.ai_procedure_hint
    val aiDiseaseHintRes
        get() = R.string.ai_disesase_hint
    val aiSuggestionHintRes
        get() = R.string.ai_suggestion_hint

    val nameRes
        get() = R.string.name
    val emailRes
        get() = R.string.email
    val passwordRes
        get() = R.string.password
    val repeatPasswordRes
        get() = R.string.repeat_password
    val dateOfBirth
        get() = R.string.date_of_birth

    val successRes
        get() = R.string.success
    val oopsRes
        get() = R.string.oops
    val veryImportant
        get() = R.string.very_important

    val doctorRes
        get() = R.string.doctor
    val pharmacyRes
        get() = R.string.pharmacy
    val clinicRes
        get() = R.string.clinic
    val serviceRes
        get() = R.string.service
    val weightRes
        get() = R.string.weigth
    val heightRes
        get() = R.string.height

    val doctorTypeRes
        get() = R.string.doctor_type
    val clinicTypeRes
        get() = R.string.clinic_type

    val invalidNameRes
        get() = R.string.invalid_name
    val invalidPassword
        get() = R.string.invalid_password
    val invalidWeight
        get() = R.string.invalid_weight
    val invalidHeight
        get() = R.string.invalid_height
    val invalidEmail
        get() = R.string.invalid_email
    val invalidPhone
        get() = R.string.invalid_phone

    fun String.capitalize(): String =
        this.replaceFirstChar { it.uppercase() }
}