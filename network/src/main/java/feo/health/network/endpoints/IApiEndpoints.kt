package feo.health.network.endpoints

sealed interface IApiEndpoints {

    interface AI {
        val GET_SUGGESTION: String
        val GET_PROCEDURE: String
        val GET_DISEASE: String
    }

    interface Auth {
        val SIGN_IN: String
        val SIGN_UP: String
        val REFRESH_TOKEN: String
    }

    sealed interface Catalog {
        interface Search {
            val SEARCH: String
        }

        interface Doctor {
            val DOCTORS: String
            val DOCTOR_SPECIALITY: String
            val DOCTOR: String
        }

        interface Disease {
            val DISEASES: String
            val DISEASE: String
        }

        interface Clinic {
            val CLINICS: String
            val CLINIC: String
            val CLINICS_BY_TYPE: String
            val CLINIC_DOCTORS: String
        }

        interface Drug {
            val DRUGS: String
            val DRUG: String
        }

        interface Services {
            val SERVICES: String
            val CLINICS_BY_SERVICE: String
        }

        interface Pharmacy {
            val PHARMACIES: String
            val VISIT_PHARMACY: String
            val PHARMACY: String
        }
    }

    interface User {
        val USER: String
        val FAVOURITES: String
        val HISTORY: String
        val PASSWORD: String
    }
}