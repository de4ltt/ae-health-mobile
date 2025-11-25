package feo.health.mobile.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import feo.health.mobile.datastore.HDataStoreImpl
import feo.health.mobile.di.AppScope
import feo.health.network.datastore.HDataStore

@Module
object DataStoreModule {

    @AppScope
    @Provides
    fun bindDataStore(context: Context): HDataStore = HDataStoreImpl(context)

}