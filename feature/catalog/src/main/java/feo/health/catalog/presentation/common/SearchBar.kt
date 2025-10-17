package feo.health.catalog.presentation.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feo.health.catalog.presentation.state.SearchBarState
import feo.health.ui.component.HButton
import feo.health.ui.component.HContainer
import feo.health.ui.component.HIcons
import feo.health.ui.component.HSearchBar
import feo.health.ui.component.HText
import feo.health.ui.theme.HTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.exp

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    state: SearchBarState,
    onSearch: () -> Unit
) {

    val isButtonVisible: Boolean by state.isButtonVisible.collectAsStateWithLifecycle()
    val isFiltersVisible: Boolean by state.isFiltersVisible.collectAsStateWithLifecycle()

    val textFieldState = rememberTextFieldState()

    LaunchedEffect(textFieldState.text) {
        state.onInput(textFieldState.text.toString())
    }

    HSearchBar.ButtonItems(
        modifier = Modifier
            .background(
                color = HTheme.colors.onBackgroundContainer,
                shape = HTheme.shapes.rounded12
            ),
        contentModifier = Modifier.padding(HTheme.padding.common),
        state = textFieldState,
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
                targetState = isFiltersVisible,
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
                                state.onFilterButtonClick()
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
                                state.onFilterButtonClick()
                            },
                        tint = HTheme.colors.primary
                    )
            }
        },
        isButtonVisible = isButtonVisible,
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
        visible = isFiltersVisible,
        enter = fadeIn() + expandIn(expandFrom = Alignment.Center),
        exit = fadeOut() + shrinkOut(shrinkTowards = Alignment.Center)
    ) {
        HContainer.Titled(
            items = mapOf(
                "Тип" to listOf("Клиники", "Врачи", "Услуги", "Аптеки", "Евреи"),
                "Америка" to listOf("Клиники", "Врачи", "Услуги", "Аптеки"),
                "Жесть какая-то" to listOf(
                    "Клиники",
                    "Врачи",
                    "Услуги",
                    "Аптеки Кубани",
                    "Клиники",
                    "Врачи",
                    "Услуги",
                    "Аптеки"
                )
            )
        ) { text ->
            HButton.Selectable(
                enabled = text == "Клиники",
                modifier = Modifier,
                disabledContentColor = HTheme.colors.primary,
                disabledContainerColor = HTheme.colors.background,
                contentColor = HTheme.colors.background,
                containerColor = HTheme.colors.primary,
                contentPadding = HTheme.padding.common,
                onClick = { },
                content = {
                    HText.Default(
                        fontWeight = FontWeight.Medium,
                        color = { it },
                        text = text
                    )
                }
            )
        }
    }
}