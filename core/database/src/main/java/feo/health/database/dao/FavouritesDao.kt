package feo.health.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import feo.health.database.entity.FavouriteEntity
import kotlinx.coroutines.flow.Flow

/**
 * Room Data Access Object (DAO) managing transactions for the [FavouriteEntity] favourites table.
 */
@Dao
interface FavouritesDao {

    /**
     * Exposes a hot stream of all saved favorites sorted by ID.
     *
     * @return Hot [Flow] flow of [FavouriteEntity] list.
     */
    @Query("SELECT * FROM favourites")
    fun getFavouritesFlow(): Flow<List<FavouriteEntity>>

    /**
     * Queries all saved favorites as a synchronous list.
     *
     * @return List of [FavouriteEntity] items.
     */
    @Query("SELECT * FROM favourites")
    suspend fun getFavouritesList(): List<FavouriteEntity>

    /**
     * Inserts list of favorites, replacing existing conflicting entries.
     *
     * @param items Favorite items list.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourites(items: List<FavouriteEntity>): List<Long>

    /**
     * Deletes a specific favorite by its identifier.
     *
     * @param itemId Favorite item unique key.
     */
    @Query("DELETE FROM favourites WHERE id = :itemId")
    suspend fun deleteFavourite(itemId: String): Int

    /**
     * Wipes out all entries inside the favourites table.
     */
    @Query("DELETE FROM favourites")
    suspend fun clearFavourites(): Int
}
