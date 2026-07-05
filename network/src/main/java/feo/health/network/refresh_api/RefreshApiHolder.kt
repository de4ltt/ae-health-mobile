package feo.health.network.refresh_api

import feo.health.network.di.NetworkModuleScope
import javax.inject.Inject

@NetworkModuleScope
class RefreshApiHolder @Inject constructor() {
    @Volatile
    var refreshApi: IRefreshApi? = null
}
