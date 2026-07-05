package feo.health.ai.data.di

import feo.health.ai.domain.repository.IAiRepository

interface AiRepositoryProvider {
    fun aiRepository(): IAiRepository
}
