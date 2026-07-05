package feo.health.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import feo.health.database.HDatabase
import feo.health.database.dao.FavouritesDao
import feo.health.database.dao.HistoryDao

/**
 * Dagger module providing singleton database and DAO instance binders.
 */
@Module
object DatabaseModule {

    /**
     * Instantiates the Room database database instance.
     *
     * @param context Application context descriptor.
     * @return Constructed singleton [HDatabase] database.
     */
    @CoreDatabaseScope
    @Provides
    fun provideDatabase(context: Context): HDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            HDatabase::class.java,
            "aehealth_database.db"
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    /**
     * Exposes the [FavouritesDao] data transaction interface.
     *
     * @param database The target Room database.
     * @return Resolved favourites DAO.
     */
    @CoreDatabaseScope
    @Provides
    fun provideFavouritesDao(database: HDatabase): FavouritesDao = database.favouritesDao()

    /**
     * Exposes the [HistoryDao] data transaction interface.
     *
     * @param database The target Room database.
     * @return Resolved history DAO.
     */
    @CoreDatabaseScope
    @Provides
    fun provideHistoryDao(database: HDatabase): HistoryDao = database.historyDao()
}
