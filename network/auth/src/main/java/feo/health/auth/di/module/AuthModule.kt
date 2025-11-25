package feo.health.auth.di.module

import dagger.Binds
import dagger.Module
import feo.health.auth.api.AuthApi
import feo.health.auth.api.IAuthApi
import feo.health.auth.api.RefreshApi
import feo.health.auth.di.NetworkAuthScope
import feo.health.network.refresh_api.IRefreshApi

@Module
internal abstract class AuthModule {

    @NetworkAuthScope
    @Binds
    abstract fun bindAuthApi(authApi: AuthApi): IAuthApi

    @NetworkAuthScope
    @Binds
    abstract fun bindRefreshApi(refreshApi: RefreshApi): IRefreshApi

}