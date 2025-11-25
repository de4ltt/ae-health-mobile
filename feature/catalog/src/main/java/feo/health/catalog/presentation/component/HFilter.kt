package feo.health.catalog.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feo.health.catalog.presentation.viewmodel.companion.SearchBarState
import feo.health.ui.component.HText
import feo.health.ui.component.container.HContainer
import feo.health.ui.theme.HTheme

object HFilter {

    @Composable
    operator fun invoke() {

        val allowedType by SearchBarState.FiltersState.allowedType.collectAsStateWithLifecycle()
        val allowedSorting by SearchBarState.FiltersState.allowedSorting.collectAsStateWithLifecycle()
        val allowedRadius by SearchBarState.FiltersState.allowedRadius.collectAsStateWithLifecycle()

        val selectedType by SearchBarState.FiltersState.selectedType.collectAsStateWithLifecycle()
        val selectedSorting by SearchBarState.FiltersState.selectedSorting.collectAsStateWithLifecycle()
        val selectedRadius by SearchBarState.FiltersState.selectedRadius.collectAsStateWithLifecycle()

        val entries = mapOf(
            SearchBarState.Filters.Type.groupTitle to Triple(
                SearchBarState.Filters.Type.entries,
                allowedType,
                selectedType
            ),
/*            SearchBarState.Filters.Sorting.groupTitle to Triple(
                SearchBarState.Filters.Sorting.entries,
                allowedSorting,
                selectedSorting
            ),
            SearchBarState.Filters.Radius.groupTitle to Triple(
                SearchBarState.Filters.Radius.entries,
                allowedRadius,
                selectedRadius
            ),*/
        )

        HContainer.Default(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .animateContentSize()
        ) {
            entries.entries.forEach { entry ->

                val title = entry.key
                val allItems = entry.value.first
                val allowedItems = entry.value.second
                val selectedItems = entry.value.third

                AnimatedVisibility(
                    visible = allowedItems.isNotEmpty(),
                    exit = shrinkVertically(tween(delayMillis = 200))
                ) {
                    val allChar = allItems.sumOf { it.title.chars().count() }.toFloat()

                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        HText.SingleLine(text = title, fontWeight = FontWeight.SemiBold)
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .animateContentSize(),
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            allItems.chunked(3).forEach { rowItems ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    rowItems.forEach { item ->
                                        key(item.title) {
                                            val fraction =
                                                if (allChar > 0) item.title.chars().count()
                                                    .toFloat() / allChar else 1f / 3f

                                            val selected = selectedItems.contains(item)
                                            val textColor by animateColorAsState(if (selected) HTheme.colors.background else HTheme.colors.primary)
                                            val backgroundColor by animateColorAsState(if (selected) HTheme.colors.primary else HTheme.colors.background)
                                            AnimatedVisibility(
                                                modifier = Modifier
                                                    .then(
                                                        if (rowItems.size == 1)
                                                            Modifier.wrapContentWidth()
                                                        else Modifier.weight(fraction * 3f).fillMaxWidth()
                                                    ),
                                                visible = allowedItems.contains(item),
                                                enter = scaleIn(),
                                                exit = fadeOut()
                                            ) {
                                                HContainer.Default(
                                                    modifier = Modifier
                                                        .clickable(
                                                            onClick = {
                                                                SearchBarState.FiltersState.switchFilter(
                                                                    item
                                                                )
                                                            },
                                                            indication = null,
                                                            interactionSource = null
                                                        ),
                                                    backgroundColor = backgroundColor
                                                ) {
                                                    HText.SingleLine(
                                                        text = item.title,
                                                        color = textColor,
                                                        fontWeight = FontWeight.Normal
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}