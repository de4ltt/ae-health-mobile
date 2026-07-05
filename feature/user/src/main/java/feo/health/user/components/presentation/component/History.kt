package feo.health.user.components.presentation.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import feo.health.ui.component.CatalogItem.CatalogItem
import feo.health.ui.component.CatalogItem.ShimmerCatalogItem
import feo.health.ui.component.HText
import feo.health.ui.component.container.HContainer
import feo.health.ui.component.container.HList
import feo.health.ui.model.ICatalogItem
import feo.health.ui.util.ILoading
import feo.health.ui.util.capitalize
import feo.health.user.components.presentation.model.UCatalogItem
import feo.health.user.components.presentation.viewmodel.companion.UserEvent

/**
 * Component representing the History screen.
 * Implements [ILoading] to provide a shimmer loading placeholder screen.
 */
object History : ILoading {

    /**
     * Composable screen displaying the user's viewing history items grouped by date/category.
     *
     * @param historyItems A map where the key is the group title and the value is a list of [UCatalogItem]s.
     * @param navHostController The navigation controller used for screen transitions.
     * @param onEvent Callback to handle user events such as item clicks.
     */
    @Composable
    fun Screen(
        historyItems: Map<String, List<UCatalogItem>>,
        navHostController: NavHostController,
        onEvent: (UserEvent) -> Unit
    ) = HContainer.TitledScreen(
        modifier = Modifier.fillMaxSize(),
        title = stringResource(feo.health.ui.R.string.history).capitalize()
    ) {
        historyItems.forEach { entry ->
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

    /**
     * Composable screen displaying a shimmer/placeholder UI while history items are loading.
     *
     * @param params Optional arguments for customizing the loading screen.
     */
    @Composable
    override fun LoadingScreen(vararg params: Any) = HContainer.TitledScreen(
        modifier = Modifier.fillMaxSize(),
        title = stringResource(feo.health.ui.R.string.history).capitalize()
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