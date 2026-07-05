package feo.health.catalog.data.repository

import java.util.Collections

/**
 * Thread-safe Least Recently Used (LRU) cache implementation using Java's LinkedHashMap.
 *
 * @param K Type of keys.
 * @param V Type of values.
 * @property maxEntries The maximum number of entries to keep in cache.
 */
internal class RepositoryCache<K, V>(private val maxEntries: Int) {
    
    private val cacheMap = Collections.synchronizedMap(
        object : LinkedHashMap<K, V>(maxEntries, 0.75f, true) {
            override fun removeEldestEntry(eldest: Map.Entry<K, V>?): Boolean {
                return size > maxEntries
            }
        }
    )

    /**
     * Retrieves an entry from cache.
     *
     * @param key Entry search key.
     * @return Cached value, or `null` if not found.
     */
    fun get(key: K): V? = cacheMap[key]

    /**
     * Puts a value into the cache.
     *
     * @param key Entry key.
     * @param value Entry value.
     */
    fun put(key: K, value: V) {
        cacheMap[key] = value
    }
}
