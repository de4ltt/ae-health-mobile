package feo.health.secrets

/**
 * Contract representing secure secrets and API keys required by the application.
 */
interface HSecrets {
    /**
     * API key used for 2GIS maps and location APIs.
     */
    val twoGISApiKey: String

    /**
     * The primary domain/URL used as base API endpoint.
     */
    val domain: String
}