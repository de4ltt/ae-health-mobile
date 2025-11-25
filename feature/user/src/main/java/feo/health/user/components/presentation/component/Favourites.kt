package feo.health.user.components.presentation.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.valentinilk.shimmer.shimmer
import feo.health.ui.component.CatalogItem.CatalogItem
import feo.health.ui.component.CatalogItem.ShimmerCatalogItem
import feo.health.ui.component.HText
import feo.health.ui.component.container.HContainer
import feo.health.ui.component.container.HList
import feo.health.ui.model.ICatalogItem
import feo.health.ui.resource.HStrings
import feo.health.ui.resource.HStrings.capitalize
import feo.health.ui.util.ILoading
import feo.health.user.components.presentation.model.UCatalogItem
import feo.health.user.components.presentation.viewmodel.companion.UserEvent

object Favourites : ILoading {

    @Composable
    fun Screen(
        favouriteItems: Map<String, List<UCatalogItem>>,
        navHostController: NavHostController,
        onEvent: (UserEvent) -> Unit
    ) = HContainer.TitledScreen(
        modifier = Modifier.fillMaxSize(),
        title = HStrings.favourites.capitalize()
    ) {
        favouriteItems.forEach { entry ->
            HText.SingleLine(
                text = entry.key
            )
            HList.Lazy(
                modifier = Modifier,
                contentPadding = PaddingValues.Zero,
                spacing = 10.dp,
                items = entry.value,
                itemContainer = { item ->
                    CatalogItem(
                        item = item as ICatalogItem,
                        onClick = {
                            onEvent(UserEvent.OnItemDetails(item))
                        }
                    )
                }
            )
        }
    }

    @Composable
    override fun LoadingScreen(vararg params: Any) = HContainer.TitledScreen(
        modifier = Modifier.fillMaxSize(),
        title = HStrings.favourites.capitalize()
    ) {
        HList.Lazy(
            modifier = Modifier,
            contentPadding = PaddingValues.Zero,
            spacing = 10.dp,
            items = List(20) {},
            itemContainer = { item -> ShimmerCatalogItem() }
        )
    }
}