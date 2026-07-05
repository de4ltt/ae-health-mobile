package feo.health.network.refresh_api

import feo.health.network.di.NetworkModuleScope
import javax.inject.Inject

/**
 * Thread-safe holder storing the active implementation of [IRefreshApi].
 * Avoids circular dependencies in Dagger graphs between the HTTP Client and Authentication APIs.
 */
@NetworkModuleScope
class RefreshApiHolder @Inject constructor() {
    /**
     * Active token refresh api handler.
     */
    @Volatile
    var refreshApi: IRefreshApi? = null
}
