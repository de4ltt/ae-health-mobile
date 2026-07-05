package feo.health.network.endpoints

/**
 * API route endpoint mapping constants representing network paths.
 */
object ApiEndpoints {

    /**
     * Active host address of the server API.
     */
    const val HOST = "http://10.0.2.2:8080"

    /**
     * Base path directory prefix of the API endpoints.
     */
    const val BASE_URL = "$HOST/api/v1"

    /**
     * AI-assistant query endpoints.
     */
    object AI {
        private const val AI = "$BASE_URL/ai"

        /**
         * Post query to fetch symptom/recommendation suggestions.
         */
        const val POST_SUGGESTION = "$AI/suggestion"

        /**
         * Post query to fetch details on medical procedures.
         */
        const val POST_PROCEDURE = "$AI/procedure"

        /**
         * Post query to check disease diagnostics.
         */
        const val POST_DISEASE = "$AI/disease"
    }

    /**
     * Authentication session query endpoints.
     */
    object Auth {
        private const val AUTH = "$BASE_URL/auth"

        /**
         * Post login session request.
         */
        const val SIGN_IN =  "$AUTH/sign-in"

        /**
         * Post signup session request.
         */
        const val SIGN_UP = "$AUTH/sign-up"

        /**
         * Post request to refresh access token using refresh tokens.
         */
        const val REFRESH_TOKEN = "$AUTH/refresh"
    }

    /**
     * Search catalog database endpoints.
     */
    object Catalog {
        private const val CATALOG = "$BASE_URL/catalog"

        /**
         * Get query matching search bar entries.
         */
        const val GET_SEARCH = "$CATALOG/search"

        /**
         * Medical clinics directories endpoints.
         */
        object Clinic {
            private const val CLINIC = "$CATALOG/clinics"

            /**
             * Get clinics list.
             */
            const val GET_CLINICS = CLINIC

            /**
             * Get specific clinic info by URL link parameter.
             */
            const val GET_CLINIC = "$CLINIC/{link}"

            /**
             * Get clinics offering specific services/specialty parameters.
             */
            const val GET_CLINICS_BY_TYPE = "$CLINIC/{link}/clinics"

            /**
             * Get list of doctors belonging to clinic.
             */
            const val GET_CLINIC_DOCTORS = "$CLINIC/{link}/doctors"
        }

        /**
         * Diseases directory endpoints.
         */
        object Disease {
            private const val DISEASE = "$CATALOG/diseases"

            /**
             * Get diseases list.
             */
            const val GET_DISEASES = DISEASE

            /**
             * Get disease details by URL link identifier.
             */
            const val GET_DISEASE = "$DISEASE/{link}"
        }

        /**
         * Doctors directories endpoints.
         */
        object Doctor {
            private const val DOCTOR = "$CATALOG/doctors"

            /**
             * Get doctors list.
             */
            const val GET_DOCTORS = DOCTOR

            /**
             * Get doctors list by specialization type parameter.
             */
            const val GET_DOCTOR_BY_SPECIALITY = "$DOCTOR/speciality/{link}"

            /**
             * Get specific doctor details by URL link identifier.
             */
            const val GET_DOCTOR = "$DOCTOR/{link}"

            /**
             * Get clinics list associated with specific doctor.
             */
            const val GET_DOCTOR_CLINICS = "$DOCTOR/{link}/clinics"
        }

        /**
         * Drug medications catalog endpoints.
         */
        object Drug {
            private const val DRUG = "$CATALOG/drugs"

            /**
             * Get drugs list.
             */
            const val GET_DRUGS = DRUG

            /**
             * Get drug details by URL link identifier.
             */
            const val GET_DRUG = "$DRUG/{link}"
        }

        /**
         * Pharmacies directories endpoints.
         */
        object Pharmacy {
            private const val PHARMACY = "$CATALOG/pharmacies"

            /**
             * Get pharmacies list.
             */
            const val GET_PHARMACIES = PHARMACY

            /**
             * Log user check-in/visit to a pharmacy.
             */
            const val POST_VISIT_PHARMACY = "$PHARMACY/visit"

            /**
             * Get pharmacy details by URL link identifier.
             */
            const val GET_PHARMACY = "$PHARMACY/{link}"
        }

        /**
         * Medical services directories endpoints.
         */
        object Services {
            private const val SERVICE = "$CATALOG/services"

            /**
             * Get services list.
             */
            const val GET_SERVICES = SERVICE

            /**
             * Get clinics list associated with specific medical service.
             */
            const val GET_CLINICS_BY_SERVICE = "$SERVICE/{link}/clinics"
        }
    }

    /**
     * Logged-in user profile, favorites, and history endpoints.
     */
    object User {
        private const val USER = "$BASE_URL/user"

        /**
         * Fetch current logged-in user profile attributes.
         */
        const val GET_USER = USER

        /**
         * Update user profile attributes.
         */
        const val PUT_USER = USER

        /**
         * Delete active user profile/account properties.
         */
        const val DELETE_USER = USER

        /**
         * Request profile account security password changes.
         */
        const val POST_CHANGE_PASSWORD = "$USER/password"

        /**
         * Get user's favorites directory elements.
         */
        const val GET_FAVOURITES = "$USER/favourites"

        /**
         * Add item to user favorites list.
         */
        const val POST_FAVOURITE = "$USER/favourites"

        /**
         * Delete item from user favorites list.
         */
        const val DELETE_FAVOURITE = "$USER/favourites"

        /**
         * Fetch user's consulting action history records.
         */
        const val GET_HISTORY = "$USER/history"

        /**
         * Clear consult history record list.
         */
        const val DELETE_HISTORY = "$USER/history"
    }
}