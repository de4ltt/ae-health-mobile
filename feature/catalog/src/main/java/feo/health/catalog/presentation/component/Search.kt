package feo.health.catalog.presentation.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feo.health.catalog.presentation.util.Mock
import feo.health.catalog.presentation.viewmodel.companion.CatalogEvent
import feo.health.catalog.presentation.viewmodel.companion.CatalogState
import feo.health.catalog.presentation.viewmodel.companion.SearchBarState
import feo.health.ui.component.CatalogItem.CatalogItem
import feo.health.ui.component.CatalogItem.ShimmerCatalogItem
import feo.health.ui.component.HTextBar
import feo.health.ui.component.NavAnchors
import feo.health.ui.component.container.HList
import feo.health.ui.resource.HIcons
import feo.health.ui.resource.HStrings
import feo.health.ui.resource.HStrings.capitalize
import feo.health.ui.theme.HTheme
import feo.health.ui.util.ILoading
import kotlinx.coroutines.flow.StateFlow

object Search : ILoading {

    @Composable
    fun Screen(
        modifier: Modifier = Modifier,
        screenState: StateFlow<CatalogState>,
        onEvent: (CatalogEvent) -> Unit
    ) {

        LaunchedEffect(Unit) {
            NavAnchors.show()
        }

        val screenState by screenState.collectAsStateWithLifecycle()

        Column(
            modifier = modifier
                .fillMaxSize()
                .animateContentSize()
        ) {
            if (screenState is CatalogState.Items.Found)
                HList.LazyTitled(
                    modifier = Modifier,
                    contentPadding = PaddingValues.Zero,
                    spacing = 10.dp,
                    title = HStrings.hereIsWhatWeFound.capitalize(),
                    items = (screenState as CatalogState.Items.Found).found,
                    itemContainer = { item ->
                        CatalogItem(
                            item = item,
                            onClick = { onEvent(CatalogEvent.ItemInfoEvent.OnDetails(item = item)) }
                        )
                    }
                )
        }
    }

    @Composable
    fun SearchBar(
        onSearch: () -> Unit
    ) = Column {

        val isButtonVisible: Boolean by SearchBarState.isButtonVisible.collectAsStateWithLifecycle()
        val isFiltersVisible: Boolean by SearchBarState.isFiltersVisible.collectAsStateWithLifecycle()
        val isInputAllowed: Boolean by SearchBarState.isInputAllowed.collectAsStateWithLifecycle()

        val initialText = SearchBarState.input.collectAsState().value
        val textFieldState = rememberTextFieldState(initialText = initialText)

        LaunchedEffect(textFieldState.text) {
            SearchBarState.onInput(textFieldState.text.toString())
        }

        HTextBar.ButtonItems(
            modifier = Modifier
                .background(
                    color = HTheme.colors.onBackgroundContainer,
                    shape = HTheme.shapes.rounded12
                ),
            contentModifier = Modifier.padding(HTheme.padding.common),
            isButtonVisible = isInputAllowed && isButtonVisible,
            state = textFieldState,
            enabled = isInputAllowed,
            textStyle = HTheme.typography.medium.copy(color = HTheme.colors.secondary),
            lineLimits = TextFieldLineLimits.SingleLine,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            frontItem = {
                HIcons.SEARCH(
                    modifier = Modifier.size(25.dp),
                    tint = HTheme.colors.secondary
                )
            },
            backItem = {
                AnimatedContent(
                    modifier = Modifier.size(25.dp),
                    targetState = isFiltersVisible && isInputAllowed,
                    transitionSpec = {
                        (fadeIn() + scaleIn() + expandIn()).togetherWith(
                            fadeOut() + scaleOut() + shrinkOut()
                        )
                    }
                ) { targetState ->
                    if (!targetState)
                        HIcons.FILTER(
                            modifier = Modifier
                                .size(25.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    SearchBarState.onFilterButtonClick()
                                },
                            tint = HTheme.colors.secondary
                        )
                    else
                        HIcons.ARROW_UP(
                            modifier = Modifier
                                .size(25.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    SearchBarState.onFilterButtonClick()
                                },
                            tint = HTheme.colors.primary
                        )
                }
            },
            onSearch = onSearch,
        )

        val animatedPadding by animateDpAsState(
            if (isFiltersVisible) 10.dp else 0.dp,
            animationSpec = tween(850)
        )

        AnimatedVisibility(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = animatedPadding),
            visible = isFiltersVisible && isInputAllowed,
            enter = fadeIn() + expandIn(expandFrom = Alignment.Center),
            exit = fadeOut() + shrinkOut(shrinkTowards = Alignment.Center)
        ) { HFilter() }
    }

    @Composable
    override fun LoadingScreen(vararg params: Any) =
        HList.ShimmerTitled(
            modifier = Modifier,
            contentPadding = PaddingValues.Zero,
            spacing = 10.dp,
            items = List(20) { Mock.specialists },
            itemContainer = { item -> ShimmerCatalogItem() }
        )
}