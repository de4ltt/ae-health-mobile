package feo.health.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object HList {

    enum class ListOrientation {
        HORIZONTAL,
        VERTICAL
    }

    @Composable
    fun <T> Lazy(
        modifier: Modifier = Modifier,
        listOrientation: ListOrientation = ListOrientation.VERTICAL,
        contentPadding: PaddingValues = PaddingValues(10.dp),
        spacing: Dp = 10.dp,
        items: List<T>,
        itemContainer: @Composable (T) -> Unit
    ) = when (listOrientation) {
        ListOrientation.HORIZONTAL -> LazyRow(
            modifier = modifier,
            contentPadding = contentPadding,
            horizontalArrangement = Arrangement.spacedBy(spacing),
            content = { items(items) { itemContainer(it) } }
        )
        ListOrientation.VERTICAL -> LazyColumn(
            modifier = modifier,
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(spacing),
            content = { items(items) { itemContainer(it) } }
        )
    }

    @Composable
    fun <T> Default() {

    }

    @Composable
    fun <T> LazyTitled(
        modifier: Modifier = Modifier,
        listOrientation: ListOrientation = ListOrientation.VERTICAL,
        contentPadding: PaddingValues = PaddingValues(10.dp),
        spacing: Dp = 10.dp,
        title: String,
        items: List<T>,
        itemContainer: @Composable (T) -> Unit
    ) = Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        HText.Default(
            fontWeight = FontWeight.SemiBold,
            text = title,
            fontSize = 24.sp
        )

        Lazy(
            modifier = modifier,
            listOrientation = listOrientation,
            contentPadding = contentPadding,
            spacing = spacing,
            items = items,
            itemContainer = itemContainer
        )
    }
}