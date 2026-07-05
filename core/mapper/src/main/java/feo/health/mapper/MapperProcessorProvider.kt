package feo.health.mapper

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

/**
 * Provider factory implementation that instantiates [MapperProcessor] during the KSP processing round.
 */
class MapperProcessorProvider: SymbolProcessorProvider {
    /**
     * Instantiates a new symbol processor instance using KSP environment variables.
     *
     * @param environment KSP symbol processing surroundings context.
     * @return A newly built [MapperProcessor] instance.
     */
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor =
        MapperProcessor(environment.codeGenerator, environment.logger)
}