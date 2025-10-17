package feo.health.datastore.datastore.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import feo.health.datastore.R
import feo.health.datastore.datastore.di.DatastoreScope
import io.github.cdimascio.dotenv.Dotenv
import java.io.File

@Module
object DotenvModule {

    @DatastoreScope
    @Provides
    fun provideDotenv(context: Context): Dotenv {
        val tempFile = File(context.cacheDir, "env.env").apply {
            context.resources.openRawResource(R.raw.env).use { input ->
                outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        }

        return Dotenv.configure()
            .directory(tempFile.parent)
            .filename(tempFile.name)
            .load()
    }
}