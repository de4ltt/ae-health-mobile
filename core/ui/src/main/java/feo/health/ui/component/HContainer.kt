package feo.health.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import feo.health.ui.theme.HTheme
import feo.health.ui.theme.HealthTheme

object HContainer {

    @Composable
    fun Default(
        modifier: Modifier = Modifier,
        shape: Shape = HTheme.shapes.rounded12,
        backgroundColor: Color = HTheme.colors.onBackgroundContainer,
        content: @Composable ColumnScope.() -> Unit
    ) {

        val paddingValues = PaddingValues(15.dp)

        Box(
            modifier = modifier
                .wrapContentSize()
                .background(color = backgroundColor, shape = shape),
            content = {
                Column(
                    modifier = Modifier.padding(paddingValues),
                    content = content,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                )
            },
        )
    }

    @Composable
    fun <T> Titled(
        modifier: Modifier = Modifier,
        shape: Shape = HTheme.shapes.rounded12,
        backgroundColor: Color = HTheme.colors.onBackgroundContainer,
        items: Map<String, List<T>>,
        itemContainer: @Composable (T) -> Unit
    ) = Default(modifier = modifier, backgroundColor = backgroundColor, shape = shape) {
        items.forEach { entry ->
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                HText.Default(
                    fontWeight = FontWeight.Medium,
                    text = entry.key,
                    fontSize = 16.sp
                )

                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    entry.value.forEach { item ->
                        itemContainer(item)
                    }
                }
            }
        }
    }
}
