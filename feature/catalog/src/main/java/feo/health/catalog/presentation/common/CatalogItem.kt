package feo.health.catalog.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import feo.health.catalog.presentation.model.util.ICatalogItem
import feo.health.ui.component.HStrings.capitalize
import feo.health.ui.component.HText
import feo.health.ui.theme.HTheme

@Composable
fun <T: ICatalogItem> CatalogItem(
    item: T
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(
                    color = HTheme.colors.onBackgroundContainer,
                    shape = HTheme.shapes.rounded12
                )
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            val onBackground = HTheme.colors.onBackground
            val secondary = HTheme.colors.secondary

            HText.BasicMarquee(
                fontSize = 18.sp,
                text = item.title,
                color = { onBackground }
            )

            HText.Default(
                fontSize = 16.sp,
                text = stringResource(item.type.title).capitalize(),
                color = { secondary }
            )
        }
    }
}