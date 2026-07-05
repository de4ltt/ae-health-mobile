package feo.health.user.di.module

import dagger.Binds
import dagger.Module
import feo.health.user.api.IUserApi
import feo.health.user.api.UserApi
import feo.health.user.di.NetworkUserScope

/**
 * Dagger module binding concrete user API client implementation to its contract interface.
 */
@Module
internal abstract class UserModule {

    /**
     * Binds the remote user API client.
     *
     * @param userApi Concrete api implementation.
     * @return Bounded interface.
     */
    @NetworkUserScope
    @Binds
    abstract fun bindUserApi(userApi: UserApi): IUserApi
}