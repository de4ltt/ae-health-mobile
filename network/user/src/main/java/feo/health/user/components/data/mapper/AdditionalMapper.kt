package feo.health.user.components.data.mapper

import feo.health.database.entity.FavouriteEntity
import feo.health.database.entity.HistoryEntity
import feo.health.user.components.data.mapper.CatalogItemDomainToCatalogItemResponseMapper.toDomainList
import feo.health.user.components.domain.model.CatalogItemDomain
import feo.health.user.dto.request.CatalogItemRequest
import feo.health.user.dto.response.CatalogItemResponse
import java.time.LocalDateTime

/**
 * Helper mapper extensions handling collection-level user data mapping structures.
 */
internal object AdditionalMapper {
    /**
     * Converts a map of catalog item lists to their domain models representation.
     *
     * @return Resolved map containing domain lists.
     */
    fun Map<String, List<CatalogItemResponse>>.toDomainMap() =
        this.mapValues { value -> value.value.toDomainList() }

    /**
     * Converts a [CatalogItemDomain] domain entity to its corresponding serial [CatalogItemRequest] model.
     *
     * @return Resolved [CatalogItemRequest].
     */
    fun CatalogItemDomain.toDto() = CatalogItemRequest(
        type = type,
        link = link
    )

    /**
     * Converts a [FavouriteEntity] Room entity into its domain representation [CatalogItemDomain].
     *
     * @return Resolved domain model.
     */
    fun FavouriteEntity.toDomain() = CatalogItemDomain(
        name = name,
        type = type,
        link = link,
        imageUri = imageUri,
        dateTime = try { LocalDateTime.parse(dateTime) } catch (e: Exception) { LocalDateTime.now() }
    )

    /**
     * Converts a [CatalogItemDomain] domain entity into its corresponding Room [FavouriteEntity] model.
     *
     * @param id Unique key.
     * @param groupKey Category group key map identifier.
     * @return Resolved Room model.
     */
    fun CatalogItemDomain.toEntity(id: String, groupKey: String) = FavouriteEntity(
        id = id,
        groupKey = groupKey,
        name = name,
        type = type,
        link = link,
        imageUri = imageUri,
        dateTime = dateTime.toString()
    )

    /**
     * Converts a [HistoryEntity] Room entity into its domain representation [CatalogItemDomain].
     *
     * @return Resolved domain model.
     */
    fun HistoryEntity.toDomain() = CatalogItemDomain(
        name = name,
        type = type,
        link = link,
        imageUri = imageUri,
        dateTime = try { LocalDateTime.parse(dateTime) } catch (e: Exception) { LocalDateTime.now() }
    )

    /**
     * Converts a [CatalogItemDomain] domain entity into its corresponding Room [HistoryEntity] model.
     *
     * @param id Unique key.
     * @param groupKey Category group key map identifier.
     * @return Resolved Room model.
     */
    fun CatalogItemDomain.toEntityHistory(id: String, groupKey: String) = HistoryEntity(
        id = id,
        groupKey = groupKey,
        name = name,
        type = type,
        link = link,
        imageUri = imageUri,
        dateTime = dateTime.toString()
    )
}