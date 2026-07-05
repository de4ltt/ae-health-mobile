package feo.health.ui.util

/**
 * Capitalizes the first character of the string receiver.
 *
 * @return The capitalized string representation.
 */
fun String.capitalize(): String =
    this.replaceFirstChar { it.uppercase() }
