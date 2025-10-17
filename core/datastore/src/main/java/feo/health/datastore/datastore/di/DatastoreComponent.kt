package feo.health.datastore.datastore.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import feo.health.common.model.datastore.IpStorage
import feo.health.datastore.datastore.di.module.DotenvModule
import feo.health.datastore.datastore.di.module.IpStorageModule
import io.github.cdimascio.dotenv.Dotenv

@DatastoreScope
@Component(modules = [IpStorageModule::class, DotenvModule::class])
interface DatastoreComponent {

    fun ipStorage(): IpStorage

    fun dotenv(): Dotenv

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindContext(context: Context): Builder

        @BindsInstance
        fun bindApp(app: Application): Builder

        fun build(): DatastoreComponent
    }
}