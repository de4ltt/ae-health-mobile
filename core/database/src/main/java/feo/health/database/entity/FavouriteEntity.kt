package feo.health.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room database entity representing a user's favorite catalog item.
 *
 * @property id Unique catalog item identifier (primary key).
 * @property groupKey The category group key this favorite belongs to.
 * @property name The name of the catalog item.
 * @property type The classification or category of the catalog item.
 * @property link The optional web URL link associated with the item.
 * @property imageUri The optional image URI path or URL.
 * @property dateTime The local timestamp when this catalog item was recorded.
 */
@Entity(tableName = "favourites")
data class FavouriteEntity(
    @PrimaryKey val id: String,
    val groupKey: String,
    val name: String,
    val type: String,
    val link: String?,
    val imageUri: String?,
    val dateTime: String
)
