package feo.health.datastore.datastore.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import feo.health.common.model.datastore.IpStorage
import feo.health.datastore.datastore.datastore.IpStorageImpl
import feo.health.datastore.datastore.di.DatastoreScope

@Module
internal object IpStorageModule {

    @DatastoreScope
    @Provides
    fun provideIpStorage(app: Application): IpStorage = IpStorageImpl(
        context = app
    )

}