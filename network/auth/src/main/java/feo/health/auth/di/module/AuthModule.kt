package feo.health.auth.di.module

import dagger.Binds
import dagger.Module
import feo.health.auth.api.AuthApi
import feo.health.auth.api.IAuthApi
import feo.health.auth.api.RefreshApi
import feo.health.auth.di.NetworkAuthScope
import feo.health.network.refresh_api.IRefreshApi

/**
 * Dagger module binding concrete auth and token refresh API client implementations to their contract interfaces.
 */
@Module
internal abstract class AuthModule {

    /**
     * Binds the remote sign in/up client API.
     *
     * @param authApi Concrete api implementations.
     * @return Bounded interface.
     */
    @NetworkAuthScope
    @Binds
    abstract fun bindAuthApi(authApi: AuthApi): IAuthApi

    /**
     * Binds the dynamic bearer token refresh API.
     *
     * @param refreshApi Concrete refresh api implementations.
     * @return Bounded interface.
     */
    @NetworkAuthScope
    @Binds
    abstract fun bindRefreshApi(refreshApi: RefreshApi): IRefreshApi
}