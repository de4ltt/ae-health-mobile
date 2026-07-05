package feo.health.ui.util

fun String.capitalize(): String =
    this.replaceFirstChar { it.uppercase() }
