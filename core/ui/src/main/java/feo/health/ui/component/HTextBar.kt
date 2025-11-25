package feo.health.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import feo.health.ui.resource.HStrings
import feo.health.ui.resource.HStrings.capitalize
import feo.health.ui.theme.HTheme
import feo.health.ui.theme.fontFamily

object HTextBar {

    @Composable
    private operator fun invoke(
        modifier: Modifier = Modifier,
        state: TextFieldState = rememberTextFieldState(),
        textStyle: TextStyle,
        enabled: Boolean = true,
        onInput: (String) -> Unit = {},
        lineLimits: TextFieldLineLimits,
        outputTransformation: OutputTransformation? = null,
        contentModifier: Modifier = Modifier,
        hintText: String = HStrings.search.capitalize(),
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        frontItem: @Composable (RowScope.() -> Unit)? = null,
        backItem: @Composable (RowScope.() -> Unit)? = null
    ) {

        val textColor = HTheme.colors.secondary

        LaunchedEffect(state.text) {
            onInput(state.text.toString())
        }

        BasicTextField(
            state = state,
            outputTransformation = outputTransformation,
            modifier = modifier,
            keyboardOptions = keyboardOptions,
            readOnly = !enabled,
            textStyle = TextStyle(
                fontSize = 15.sp,
                fontWeight = HTheme.typography.medium.fontWeight,
                fontFamily = fontFamily
            ),
            lineLimits = lineLimits,
            decorator = { innerTextField ->
                Row(
                    modifier = contentModifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    frontItem?.let { it() }
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (state.text.isEmpty())
                            HText.Default(
                                text = hintText,
                                color = textColor,
                                fontSize = HTheme.typography.medium.fontSize,
                                fontWeight = HTheme.typography.medium.fontWeight
                            )
                        innerTextField()
                    }
                    backItem?.let { it() }
                }
            }
        )
    }

    @Composable
    fun SingleLineWithFrontIcon(
        modifier: Modifier = Modifier,
        state: TextFieldState = rememberTextFieldState(),
        contentModifier: Modifier = Modifier,
        hintText: String = HStrings.search.capitalize(),
        textStyle: TextStyle = HTheme.typography.medium,
        frontItem: @Composable (RowScope.() -> Unit)? = null,
        backItem: @Composable (RowScope.() -> Unit)? = null
    ) {

        val textColor = HTheme.colors.secondary

        Box(
            modifier = modifier,
            content = {
                Row(
                    modifier = contentModifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    frontItem?.let { it() }
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (state.text.isEmpty())
                            HText.Default(
                                text = hintText,
                                color = textColor,
                                fontSize = HTheme.typography.medium.fontSize,
                                fontWeight = HTheme.typography.medium.fontWeight
                            )
                        BasicText(
                            style = textStyle,
                            maxLines = 1,
                            text = state.text.toString()
                        )

                    }
                    backItem?.let { it() }
                }
            }
        )
    }

    @Composable
    fun Default(
        modifier: Modifier = Modifier,
        state: TextFieldState = rememberTextFieldState(),
        hintText: String = HStrings.search.capitalize(),
        contentModifier: Modifier = Modifier,
        textStyle: TextStyle = HTheme.typography.medium,
        lineLimits: TextFieldLineLimits = TextFieldLineLimits.MultiLine()
    ) = invoke(
        modifier = modifier,
        state = state,
        textStyle = textStyle,
        hintText = hintText,
        lineLimits = lineLimits,
        contentModifier = contentModifier
    )

    @Composable
    fun Items(
        modifier: Modifier = Modifier,
        contentModifier: Modifier = Modifier,
        state: TextFieldState = rememberTextFieldState(),
        outputTransformation: OutputTransformation? = null,
        onInput: (String) -> Unit = {},
        hintText: String = HStrings.search.capitalize(),
        enabled: Boolean = true,
        textStyle: TextStyle = HTheme.typography.medium,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
        frontItem: @Composable (RowScope.() -> Unit)? = null,
        backItem: @Composable (RowScope.() -> Unit)? = null
    ) = invoke(
        modifier = modifier,
        outputTransformation = outputTransformation,
        state = state,
        textStyle = textStyle,
        enabled = enabled,
        hintText = hintText,
        onInput = onInput,
        keyboardOptions = keyboardOptions,
        lineLimits = lineLimits,
        contentModifier = contentModifier,
        frontItem = frontItem,
        backItem = backItem
    )

    @Composable
    fun ButtonItems(
        modifier: Modifier = Modifier,
        isButtonVisible: Boolean,
        onSearch: () -> Unit,
        contentModifier: Modifier = Modifier,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        state: TextFieldState = rememberTextFieldState(),
        enabled: Boolean = true,
        textStyle: TextStyle,
        lineLimits: TextFieldLineLimits,
        frontItem: @Composable (RowScope.() -> Unit)? = null,
        backItem: @Composable (RowScope.() -> Unit)? = null
    ) {

        val density = LocalDensity.current
        var _padding by remember {
            mutableStateOf(0.dp)
        }
        val padding by animateDpAsState(
            targetValue = if (isButtonVisible) _padding else 0.dp,
            animationSpec = tween(durationMillis = 300)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            contentAlignment = Alignment.CenterEnd
        ) {

            AnimatedVisibility(
                visible = isButtonVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                HButton.Default(
                    modifier = Modifier
                        .fillMaxHeight()
                        .onPlaced { coordinates ->
                            with(density) {
                                _padding = coordinates.size.width.toDp() + 10.dp
                            }
                        },
                    contentPadding = HTheme.padding.common,
                    onClick = onSearch,
                    content = {
                        HText.Default(
                            color = it,
                            text = HStrings.search.capitalize(),
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            }

            Items(
                modifier = Modifier
                    .padding(end = padding)
                    .then(modifier),
                contentModifier = contentModifier,
                state = state,
                enabled = enabled,
                textStyle = textStyle,
                keyboardOptions = keyboardOptions,
                lineLimits = lineLimits,
                frontItem = frontItem,
                backItem = backItem
            )
        }
    }
}