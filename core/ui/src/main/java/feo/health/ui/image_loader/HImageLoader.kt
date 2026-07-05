package feo.health.ui.image_loader

import coil3.ImageLoader

/**
 * Singleton holder providing the initialized Coil [ImageLoader] instance used throughout the app.
 */
object HImageLoader {
    /**
     * The shared global image loader instance.
     */
    lateinit var INSTANCE: ImageLoader
}