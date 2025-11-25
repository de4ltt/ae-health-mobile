package feo.health.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.toUri
import com.valentinilk.shimmer.shimmer
import feo.health.ui.component.HProgressIndicator.Shimmer.defaultShimmer
import feo.health.ui.component.container.HContainer
import feo.health.ui.model.ICatalogItem
import feo.health.ui.resource.HIcons
import feo.health.ui.resource.HStrings.capitalize
import feo.health.ui.theme.HTheme

object CatalogItem {

    @Composable
    fun <T : ICatalogItem> CatalogItem(
        item: T,
        onClick: () -> Unit = {}
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(
                    indication = null,
                    interactionSource = null,
                    onClick = onClick
                ),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            HContainer.Image.AsyncImage(
                modifier = Modifier
                    .clip(HTheme.shapes.rounded12)
                    .fillMaxWidth(0.15f)
                    .aspectRatio(1f)
                    .background(HTheme.colors.onBackgroundContainer),
                imageModifier = Modifier.fillMaxSize(),
                progressIndicator = HProgressIndicator.Shimmer,
                onError = { item.type.icon() },
                model = item.imageUri?.toUri()
            )

            Column {
                HText.BasicMarquee(
                    fontSize = 18.sp,
                    text = item.title,
                    color = HTheme.colors.onBackground
                )
                HText.Default(
                    fontSize = 16.sp,
                    text = stringResource(item.type.title).capitalize(),
                    color = HTheme.colors.secondary
                )
            }
        }
    }

    @Composable
    fun ShimmerCatalogItem() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .shimmer(defaultShimmer),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            HContainer.Default(
                modifier = Modifier
                    .fillMaxWidth(0.15f)
                    .aspectRatio(1f)
                    .clip(HTheme.shapes.rounded12),
                paddingValues = PaddingValues.Zero,
                backgroundColor = HTheme.colors.primary
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                val titleSize = with(LocalDensity.current) { 18.sp.toDp() }
                val subtitleSize = with(LocalDensity.current) { 16.sp.toDp() }

                HContainer.Default(
                    modifier = Modifier
                        .height(titleSize)
                        .fillMaxWidth(0.8f),
                    shape = HTheme.shapes.rectangular,
                    backgroundColor = HTheme.colors.primary
                )
                HContainer.Default(
                    modifier = Modifier
                        .height(subtitleSize)
                        .fillMaxWidth(0.25f),
                    shape = HTheme.shapes.rectangular,
                    backgroundColor = HTheme.colors.primary
                )
            }
        }
    }
}