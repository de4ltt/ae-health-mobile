package feo.health.database.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import feo.health.database.dao.FavouritesDao
import feo.health.database.dao.HistoryDao

/**
 * Central Dagger DI component serving as the local database access resolver.
 */
@CoreDatabaseScope
@Component(modules = [DatabaseModule::class])
interface CoreDatabaseComponent {

    /**
     * Resolves the configured [FavouritesDao] data transactions interface.
     *
     * @return Resolved favourites DAO.
     */
    fun favouritesDao(): FavouritesDao

    /**
     * Resolves the configured [HistoryDao] data transactions interface.
     *
     * @return Resolved history DAO.
     */
    fun historyDao(): HistoryDao

    /**
     * Component builder contract for assembling the [CoreDatabaseComponent].
     */
    @Component.Builder
    interface Builder {

        /**
         * Binds the application Context to the database dependency graph.
         *
         * @param context Application context context.
         * @return The Dagger [Builder] instance.
         */
        @BindsInstance
        fun bindContext(context: Context): Builder

        /**
         * Assembles and returns the [CoreDatabaseComponent].
         *
         * @return Resolved component instance.
         */
        fun build(): CoreDatabaseComponent
    }
}
