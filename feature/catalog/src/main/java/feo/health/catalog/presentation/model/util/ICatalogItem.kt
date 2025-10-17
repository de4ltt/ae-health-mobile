package feo.health.catalog.presentation.model.util

interface ICatalogItem {
    val title: String
    val imageUri: String?
    val link: String
    val type: CatalogItemType
}