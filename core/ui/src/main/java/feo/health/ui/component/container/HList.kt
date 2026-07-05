package feo.health.ui.component.container

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valentinilk.shimmer.shimmer
import feo.health.ui.component.HText
import feo.health.ui.theme.HTheme

/**
 * Helper container wrapping lists rendering patterns (both static loops and lazy loaders)
 * with horizontal or vertical layouts and titled headers.
 */
object HList {

    /**
     * Set layout alignment direction of the list.
     */
    enum class ListOrientation {
        /**
         * Rows layout direction.
         */
        HORIZONTAL,

        /**
         * Columns layout direction.
         */
        VERTICAL
    }

    /**
     * Renders a static loop layout list.
     *
     * @param T The model item type of elements in the list.
     * @param modifier The [Modifier] applied to the outer layout container.
     * @param listOrientation The direction pattern (horizontal/vertical). Defaults to [ListOrientation.VERTICAL].
     * @param contentPadding Padding applied around each nested list element box. Defaults to 10.dp.
     * @param spacing Space between adjacent elements in list. Defaults to 10.dp.
     * @param items The raw elements list data.
     * @param itemContainer Composable block rendering each list item.
     */
    @Composable
    fun <T> Default(
        modifier: Modifier = Modifier,
        listOrientation: ListOrientation = ListOrientation.VERTICAL,
        contentPadding: PaddingValues = PaddingValues(10.dp),
        spacing: Dp = 10.dp,
        items: List<T>,
        itemContainer: @Composable (T) -> Unit
    ) = when (listOrientation) {
        ListOrientation.HORIZONTAL -> Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                items.forEach {
                    Box(
                        modifier = Modifier.padding(contentPadding),
                        content = { itemContainer(it) }
                    )
                }
            }
        )

        ListOrientation.VERTICAL -> Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(spacing),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                items.forEach {
                    Box(
                        modifier = Modifier.padding(contentPadding),
                        content = { itemContainer(it) }
                    )
                }
            }
        )
    }

    /**
     * Renders a static list with a header title at the top of the container.
     *
     * @param T The list item type.
     * @param modifier The [Modifier] to apply to the list box.
     * @param listOrientation The list direction layout. Defaults to [ListOrientation.VERTICAL].
     * @param contentPadding Padding applied to each element.
     * @param fontWeight Custom typography weight for title text. Defaults to [FontWeight.SemiBold].
     * @param fontSize Size of the title text. Defaults to 24.sp.
     * @param textAlign Visual alignment of the title. Defaults to [TextAlign.Start].
     * @param spacing Distance spacing between elements.
     * @param title Title header text.
     * @param items Raw elements list data.
     * @param itemContainer Composable mapping method for items.
     */
    @Composable
    fun <T> DefaultTitled(
        modifier: Modifier = Modifier,
        listOrientation: ListOrientation = ListOrientation.VERTICAL,
        contentPadding: PaddingValues = PaddingValues(10.dp),
        fontWeight: FontWeight = FontWeight.SemiBold,
        fontSize: TextUnit = 24.sp,
        textAlign: TextAlign = TextAlign.Start,
        spacing: Dp = 10.dp,
        title: String,
        items: List<T>,
        itemContainer: @Composable (T) -> Unit
    ) = Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        HText.Default(
            modifier = Modifier.fillMaxWidth(),
            fontWeight = fontWeight,
            text = title,
            fontSize = fontSize,
            textAlign = textAlign
        )

        Default(
            modifier = modifier,
            listOrientation = listOrientation,
            contentPadding = contentPadding,
            spacing = spacing,
            items = items,
            itemContainer = itemContainer
        )
    }

    /**
     * Renders a static list with a shimmer skeleton title loader representing loading layout states.
     *
     * @param T The list item type.
     * @param modifier The [Modifier] to apply to the list box.
     * @param listOrientation Direction alignment of the elements.
     * @param contentPadding Padding around elements.
     * @param fontSize Base font size representing title placeholder height.
     * @param spacing Inter-item list spacing.
     * @param items Shimmer placeholders data count list.
     * @param itemContainer Composable mapping block rendering shimmer skeletons.
     */
    @Composable
    fun <T> ShimmerTitled(
        modifier: Modifier = Modifier,
        listOrientation: ListOrientation = ListOrientation.VERTICAL,
        contentPadding: PaddingValues = PaddingValues(10.dp),
        fontSize: TextUnit = 24.sp,
        spacing: Dp = 10.dp,
        items: List<T>,
        itemContainer: @Composable (T) -> Unit
    ) = Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        val height = with(LocalDensity.current) { fontSize.toDp() }

        HContainer.Default(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(height)
                .shimmer(),
            shape = HTheme.shapes.rectangular,
            backgroundColor = HTheme.colors.primary
        )

        Default(
            modifier = modifier,
            listOrientation = listOrientation,
            contentPadding = contentPadding,
            spacing = spacing,
            items = items,
            itemContainer = itemContainer
        )
    }

    /**
     * Renders a scrollable lazy list using LazyRow or LazyColumn wrappers.
     *
     * @param T List items generic type.
     * @param modifier The [Modifier] applied to the Lazy list container.
     * @param listOrientation The scrolling direction. Defaults to [ListOrientation.VERTICAL].
     * @param contentPadding Outer padding of the list viewport. Defaults to 10.dp.
     * @param spacing Inter-element scroll spacing. Defaults to 10.dp.
     * @param enabled Set false to disable scroll physics. Defaults to true.
     * @param items Items model elements.
     * @param itemContainer Composable builder for elements.
     */
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun <T> Lazy(
        modifier: Modifier = Modifier,
        listOrientation: ListOrientation = ListOrientation.VERTICAL,
        contentPadding: PaddingValues = PaddingValues(10.dp),
        spacing: Dp = 10.dp,
        enabled: Boolean = true,
        items: List<T>,
        itemContainer: @Composable (T) -> Unit
    ) = when (listOrientation) {
        ListOrientation.HORIZONTAL -> LazyRow(
            modifier = modifier,
            contentPadding = contentPadding,
            userScrollEnabled = enabled,
            horizontalArrangement = Arrangement.spacedBy(spacing),
            content = { items(items) { itemContainer(it) } }
        )
        ListOrientation.VERTICAL -> LazyColumn(
            modifier = modifier,
            contentPadding = contentPadding,
            userScrollEnabled = enabled,
            verticalArrangement = Arrangement.spacedBy(spacing),
            content = { items(items) { itemContainer(it) } }
        )
    }

    /**
     * Renders a scrollable lazy list with a static header title text component.
     *
     * @param T List items generic type.
     * @param modifier The [Modifier] applied to the Lazy container.
     * @param listOrientation Scrolling direction.
     * @param contentPadding Padding viewport of lazy list.
     * @param fontWeight Custom typography weight of title.
     * @param fontSize Font size of the title.
     * @param textAlign Alignment of title text.
     * @param scrollingEnabled Set false to disable scroll physics.
     * @param spacing Inter-item spacing.
     * @param title Title header text.
     * @param items Raw elements list data.
     * @param itemContainer Composable builder mapping items.
     */
    @Composable
    fun <T> LazyTitled(
        modifier: Modifier = Modifier,
        listOrientation: ListOrientation = ListOrientation.VERTICAL,
        contentPadding: PaddingValues = PaddingValues(10.dp),
        fontWeight: FontWeight = FontWeight.SemiBold,
        fontSize: TextUnit = 24.sp,
        textAlign: TextAlign = TextAlign.Start,
        scrollingEnabled: Boolean = true,
        spacing: Dp = 10.dp,
        title: String,
        items: List<T>,
        itemContainer: @Composable (T) -> Unit
    ) = Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        HText.Default(
            modifier = Modifier.fillMaxWidth(),
            fontWeight = fontWeight,
            text = title,
            fontSize = fontSize,
            textAlign = textAlign
        )

        Lazy(
            modifier = modifier,
            listOrientation = listOrientation,
            contentPadding = contentPadding,
            spacing = spacing,
            enabled = scrollingEnabled,
            items = items,
            itemContainer = itemContainer
        )
    }
}