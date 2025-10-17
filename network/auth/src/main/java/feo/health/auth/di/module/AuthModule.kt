package feo.health.auth.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import feo.health.auth.api.AuthApi
import feo.health.auth.api.IAuthApi
import feo.health.auth.di.NetworkAuthScope

@Module
internal abstract class AuthModule {

    @NetworkAuthScope
    @Binds
    abstract fun bindAuthApi(authApi: AuthApi): IAuthApi

}