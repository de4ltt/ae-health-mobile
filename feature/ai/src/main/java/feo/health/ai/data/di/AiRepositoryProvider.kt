package feo.health.ai.data.di

import feo.health.ai.domain.repository.IAiRepository

/**
 * Interface contract providing a resolution point for obtaining the concrete [IAiRepository] instance.
 */
interface AiRepositoryProvider {
    /**
     * Resolves the configured [IAiRepository] instance.
     *
     * @return AI remote services repository.
     */
    fun aiRepository(): IAiRepository
}
