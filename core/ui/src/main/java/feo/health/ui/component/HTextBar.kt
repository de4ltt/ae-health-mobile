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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import feo.health.ui.R
import feo.health.ui.theme.HTheme
import feo.health.ui.theme.fontFamily
import feo.health.ui.util.capitalize

/**
 * A custom text input field container that wraps [BasicTextField] and provides consistent
 * layout alignment, hint texts, validation structures, and animation presets.
 */
object HTextBar {

    /**
     * Core layout container of the text input bar.
     * Integrates state tracking, visual decorators, and style definitions.
     *
     * @param modifier The [Modifier] to apply to the input layout.
     * @param state The [TextFieldState] managing input text value.
     * @param textStyle Typography configurations.
     * @param enabled Set false to lock user input capabilities.
     * @param onInput Callback triggered when text state updates.
     * @param lineLimits Line constraints of the field.
     * @param outputTransformation Formatting rules (e.g. secure fields).
     * @param contentModifier Inner layout Modifier.
     * @param hintText Placeholder text.
     * @param keyboardOptions Keyboard configurations.
     * @param frontItem Start icon composable.
     * @param backItem End icon composable.
     */
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
        hintText: String = stringResource(R.string.search).capitalize(),
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
                color = HTheme.colors.onBackground,
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

    /**
     * Renders a non-editable text container representing a search or input field style,
     * typically used for navigation anchors or quick redirects.
     *
     * @param modifier The [Modifier] to be applied to the container layout.
     * @param state The [TextFieldState] holding the text content to display.
     * @param contentModifier The [Modifier] to apply to the inner row containing icon and text.
     * @param hintText Text to be displayed when the state text is empty. Defaults to "Search".
     * @param textStyle The typography style of the text. Defaults to [HTheme.typography.medium].
     * @param frontItem Composable block to render an icon or component at the start of the field.
     * @param backItem Composable block to render an icon or component at the end of the field.
     */
    @Composable
    fun SingleLineWithFrontIcon(
        modifier: Modifier = Modifier,
        state: TextFieldState = rememberTextFieldState(),
        contentModifier: Modifier = Modifier,
        hintText: String = stringResource(R.string.search).capitalize(),
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
                            style = textStyle.copy(color = HTheme.colors.onBackground),
                            maxLines = 1,
                            text = state.text.toString()
                        )

                    }
                    backItem?.let { it() }
                }
            }
        )
    }

    /**
     * Renders a basic, standard input field with multi-line or single-line limits.
     *
     * @param modifier The [Modifier] to apply to the input layout.
     * @param state The [TextFieldState] managing input text value.
     * @param hintText Text placeholder displayed when input is empty.
     * @param contentModifier The [Modifier] to apply to the inner alignment row.
     * @param textStyle The text style configuration.
     * @param lineLimits Line height constraints of the input field. Defaults to [TextFieldLineLimits.MultiLine].
     */
    @Composable
    fun Default(
        modifier: Modifier = Modifier,
        state: TextFieldState = rememberTextFieldState(),
        hintText: String = stringResource(R.string.search).capitalize(),
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

    /**
     * Renders an input field that supports custom front and back components (e.g. clear button, icons)
     * and triggers callback on state text changes.
     *
     * @param modifier The [Modifier] to apply to the input layout.
     * @param contentModifier The [Modifier] to apply to the inner container row.
     * @param state The [TextFieldState] managing input text value.
     * @param outputTransformation Transformation logic for visual formatting of output (e.g. passwords/phone numbers).
     * @param onInput Callback triggered when input text changes, providing the new raw string.
     * @param hintText Text placeholder displayed when input is empty.
     * @param enabled Controls whether user can edit the input field. Defaults to `true`.
     * @param textStyle The text style configuration.
     * @param keyboardOptions Keyboard actions and visual layouts. Defaults to [KeyboardOptions.Default].
     * @param lineLimits Line height constraints of the input field. Defaults to [TextFieldLineLimits.Default].
     * @param frontItem Composable block to render an icon or component at the start of the field.
     * @param backItem Composable block to render an icon or component at the end of the field.
     */
    @Composable
    fun Items(
        modifier: Modifier = Modifier,
        contentModifier: Modifier = Modifier,
        state: TextFieldState = rememberTextFieldState(),
        outputTransformation: OutputTransformation? = null,
        onInput: (String) -> Unit = {},
        hintText: String = stringResource(R.string.search).capitalize(),
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

    /**
     * Renders an input search bar that reveals a trailing search/submit action button
     * with sliding animations when the search button visibility state is active.
     *
     * @param modifier The [Modifier] to apply to the outer input field.
     * @param isButtonVisible Decides if the trailing search action button is shown.
     * @param onSearch Callback invoked when the search button is clicked.
     * @param contentModifier The [Modifier] to apply to the inner alignment container row.
     * @param keyboardOptions Keyboard visual layouts and options.
     * @param state The [TextFieldState] managing input text value.
     * @param enabled Controls whether user can edit the search field. Defaults to `true`.
     * @param textStyle The typography style configuration.
     * @param lineLimits Line height constraints of the input field.
     * @param frontItem Composable block to render at the start of the input field.
     * @param backItem Composable block to render at the end of the input field.
     */
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
                            text = stringResource(R.string.search).capitalize(),
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