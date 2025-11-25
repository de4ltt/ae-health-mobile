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
import androidx.compose.foundation.lazy.LazyListPrefetchStrategy
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.layout.LazyLayoutCacheWindow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valentinilk.shimmer.shimmer
import feo.health.ui.component.HText
import feo.health.ui.theme.HTheme

object HList {

    enum class ListOrientation {
        HORIZONTAL,
        VERTICAL
    }

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