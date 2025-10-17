package feo.health.user.di.module

import dagger.Binds
import dagger.Module
import feo.health.user.api.IUserApi
import feo.health.user.api.UserApi
import feo.health.user.di.NetworkUserScope

@Module
internal abstract class UserModule {

    @NetworkUserScope
    @Binds
    abstract fun bindUserApi(userApi: UserApi): IUserApi
}