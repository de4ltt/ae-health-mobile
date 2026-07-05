package feo.health.database

import androidx.room.Database
import androidx.room.RoomDatabase
import feo.health.database.dao.FavouritesDao
import feo.health.database.dao.HistoryDao
import feo.health.database.entity.FavouriteEntity
import feo.health.database.entity.HistoryEntity

/**
 * Main application Room local database implementation holding user favourites and history.
 */
@Database(
    entities = [FavouriteEntity::class, HistoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class HDatabase : RoomDatabase() {

    /**
     * Resolves the [FavouritesDao] data transactions interface.
     *
     * @return Resolved favourites DAO interface.
     */
    abstract fun favouritesDao(): FavouritesDao

    /**
     * Resolves the [HistoryDao] data transactions interface.
     *
     * @return Resolved history DAO interface.
     */
    abstract fun historyDao(): HistoryDao
}
