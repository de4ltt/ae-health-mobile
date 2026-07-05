package feo.health.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import feo.health.database.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

/**
 * Room Data Access Object (DAO) managing transactions for the [HistoryEntity] history logs table.
 */
@Dao
interface HistoryDao {

    /**
     * Exposes a hot stream of all search history logs sorted descending by their timestamp.
     *
     * @return Hot [Flow] flow of [HistoryEntity] list.
     */
    @Query("SELECT * FROM search_history ORDER BY dateTime DESC")
    fun getHistoryFlow(): Flow<List<HistoryEntity>>

    /**
     * Queries all saved history logs as a synchronous list.
     *
     * @return List of [HistoryEntity] items.
     */
    @Query("SELECT * FROM search_history ORDER BY dateTime DESC")
    suspend fun getHistoryList(): List<HistoryEntity>

    /**
     * Inserts list of history logs, replacing existing conflicting entries.
     *
     * @param items History entries list.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(items: List<HistoryEntity>): List<Long>

    /**
     * Deletes a specific history item by its identifier.
     *
     * @param itemId Log item unique key.
     */
    @Query("DELETE FROM search_history WHERE id = :itemId")
    suspend fun deleteHistoryItem(itemId: String): Int

    /**
     * Deletes history entries matching specified link or name.
     *
     * @param link Target link.
     * @param name Target name fallback.
     */
    @Query("DELETE FROM search_history WHERE link = :link OR (link IS NULL AND name = :name)")
    suspend fun deleteHistoryItemByLinkOrName(link: String?, name: String): Int

    /**
     * Wipes out all entries inside the search_history table.
     */
    @Query("DELETE FROM search_history")
    suspend fun clearHistory(): Int
}
