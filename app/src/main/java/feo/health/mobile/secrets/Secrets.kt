package feo.health.mobile.secrets

import feo.health.mobile.BuildConfig
import feo.health.secrets.HSecrets

data object Secrets : HSecrets {
    override val twoGISApiKey: String
        get() = BuildConfig.TWOGISAPIKEY
    override val domain: String
        get() = BuildConfig.DOMAIN
}
