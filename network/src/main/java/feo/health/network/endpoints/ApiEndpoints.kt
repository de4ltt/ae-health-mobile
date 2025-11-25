package feo.health.network.endpoints

object ApiEndpoints {

//    const val HOST = "https://ae-health.ru"
    const val HOST = "http://10.0.2.2:8080"
    const val BASE_URL = "$HOST/api/v1"

    object AI {
        private const val AI = "$BASE_URL/ai"

        const val POST_SUGGESTION = "$AI/suggestion"
        const val POST_PROCEDURE = "$AI/procedure"
        const val POST_DISEASE = "$AI/disease"
    }

    object Auth {
        private const val AUTH = "$BASE_URL/auth"

        const val SIGN_IN =  "$AUTH/sign-in"
        const val SIGN_UP = "$AUTH/sign-up"
        const val REFRESH_TOKEN = "$AUTH/refresh"
    }

    object Catalog {
        private const val CATALOG = "$BASE_URL/catalog"

        const val GET_SEARCH = "$CATALOG/search"

        object Clinic {
            private const val CLINIC = "$CATALOG/clinics"

            const val GET_CLINICS = CLINIC
            const val GET_CLINIC = "$CLINIC/{link}"
            const val GET_CLINICS_BY_TYPE = "$CLINIC/{link}/clinics"
            const val GET_CLINIC_DOCTORS = "$CLINIC/{link}/doctors"
        }

        object Disease {
            private const val DISEASE = "$CATALOG/diseases"

            const val GET_DISEASES = DISEASE
            const val GET_DISEASE = "$DISEASE/{link}"
        }

        object Doctor {
            private const val DOCTOR = "$CATALOG/doctors"

            const val GET_DOCTORS = DOCTOR
            const val GET_DOCTOR_BY_SPECIALITY = "$DOCTOR/speciality/{link}"
            const val GET_DOCTOR = "$DOCTOR/{link}"
            const val GET_DOCTOR_CLINICS = "$DOCTOR/{link}/clinics"
        }

        object Drug {
            private const val DRUG = "$CATALOG/drugs"

            const val GET_DRUGS = DRUG
            const val GET_DRUG = "$DRUG/{link}"
        }

        object Pharmacy {
            private const val PHARMACY = "$CATALOG/pharmacies"

            const val GET_PHARMACIES = PHARMACY
            const val POST_VISIT_PHARMACY = "$PHARMACY/visit"
            const val GET_PHARMACY = "$PHARMACY/{link}"
        }

        object Services {
            private const val SERVICE = "$CATALOG/services"

            const val GET_SERVICES = SERVICE
            const val GET_CLINICS_BY_SERVICE = "$SERVICE/{link}/clinics"
        }
    }

    object User {
        private const val USER = "$BASE_URL/user"

        const val GET_USER = USER
        const val PUT_USER = USER
        const val DELETE_USER = USER

        const val POST_CHANGE_PASSWORD = "$USER/password"

        const val GET_FAVOURITES = "$USER/favourites"
        const val POST_FAVOURITE = "$USER/favourites"
        const val DELETE_FAVOURITE = "$USER/favourites"

        const val GET_HISTORY = "$USER/history"
        const val DELETE_HISTORY = "$USER/history"
    }
}