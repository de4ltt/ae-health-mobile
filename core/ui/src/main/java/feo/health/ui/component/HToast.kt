package feo.health.ui.component

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import feo.health.ui.component.container.HContainer
import feo.health.ui.dispatcher.AppDispatchers
import feo.health.ui.resource.HIcons
import feo.health.ui.theme.HColorScheme
import feo.health.ui.theme.HTheme
import feo.health.ui.util.capitalize
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A custom notification toast system that enqueues and displays success, error, and informational
 * alert banners across the application.
 *
 * Utilizes a [Channel] to queue and display messages sequentially with built-in animations.
 */
data object HToast {

    /**
     * Internal coroutine channel queue for toast notifications.
     */
    private val toastChannel: Channel<HToastMessage> = Channel(
        capacity = 5,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    /**
     * Data wrapper for a single toast notification.
     *
     * @property toastType Visual category of the toast.
     * @property message The text content of the toast message.
     * @property length Visual display duration.
     */
    private data class HToastMessage(
        val toastType: HToastType,
        val message: String,
        val length: HToastLength
    )

    /**
     * Duration length for displaying the toast message.
     *
     * @property lengthMillis Duration in milliseconds.
     */
    enum class HToastLength(val lengthMillis: Long) {
        SHORT(1500L),
        MEDIUM(3000L),
        LONG(5000L)
    }

    /**
     * Internal style descriptor for toast types.
     *
     * @property primaryColor Accent status color.
     * @property secondaryColor Translucent background tint.
     * @property titleRes Resource ID for header title text.
     * @property icon Status vector asset icon.
     */
    private data class HToastType(
        val primaryColor: Color,
        val secondaryColor: Color,
        @param:StringRes val titleRes: Int,
        val icon: HIcons
    ) {
        /**
         * Companion object holding standard preconfigured toast presets.
         */
        companion object {
            /**
             * Green success alert theme type preset.
             */
            val SUCCESS
                get() = HToastType(
                    primaryColor = HColorScheme.Additional.GREEN,
                    secondaryColor = HColorScheme.Additional.GREEN.copy(alpha = 0.2f),
                    titleRes = feo.health.ui.R.string.success,
                    icon = HIcons.SUCCESS_CIRCLE
                )

            /**
             * Red error alert theme type preset.
             */
            val ERROR
                get() = HToastType(
                    primaryColor = HColorScheme.Additional.RED,
                    secondaryColor = HColorScheme.Additional.RED.copy(alpha = 0.2f),
                    titleRes = feo.health.ui.R.string.oops,
                    icon = HIcons.ATTENTION
                )

            /**
             * Blue information alert theme type preset.
             */
            val INFO
                get() = HToastType(
                    primaryColor = HColorScheme.Additional.BLUE,
                    secondaryColor = HColorScheme.Additional.BLUE.copy(alpha = 0.2f),
                    titleRes = feo.health.ui.R.string.very_important,
                    icon = HIcons.INFORMATION_CIRCLE
                )
        }
    }

    /**
     * Composable container that listens to the toast channel and renders active toast
     * animations at the bottom of the screen.
     *
     * Place this composable at the root layout level (e.g. within [HScaffold]).
     *
     * @param modifier The [Modifier] to be applied to the toast wrapper layout.
     */
    @Composable
    fun Toast(modifier: Modifier = Modifier) {
        var toast: HToastMessage? by remember { mutableStateOf(null) }
        var isVisible by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            for (msg in toastChannel) {
                toast = msg
                isVisible = true
                delay(toast!!.length.lengthMillis)
                isVisible = false
                delay(1000)
                toast = null
            }
        }

        AnimatedContent(
            modifier = modifier
                .padding(horizontal = 15.dp)
                .padding(bottom = 25.dp),
            targetState = isVisible
        ) { visible ->
            if (visible)
                toast?.let { invoke(it) }
        }
    }

    /**
     * Internal layout builder that constructs the toast banner card.
     *
     * @param toastMessage The active toast details to render.
     */
    @Composable
    private operator fun invoke(toastMessage: HToastMessage) = HContainer.Default(
        paddingValues = PaddingValues(10.dp),
        shape = HTheme.shapes.rounded12,
        backgroundColor = HTheme.colors.onBackgroundContainer,
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .border(
                width = 1.dp,
                color = HTheme.colors.onBackground.copy(alpha = 0.1f),
                shape = HTheme.shapes.rounded12
            ),
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(HTheme.shapes.rounded12),
                thickness = 3.dp,
                color = toastMessage.toastType.primaryColor
            )
            HContainer.Default(
                backgroundColor = toastMessage.toastType.secondaryColor,
                paddingValues = PaddingValues.Zero,
                shape = HTheme.shapes.circular,
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .aspectRatio(1f),
                content = {
                    toastMessage.toastType.icon.invoke(
                        modifier = Modifier.fillMaxSize(0.6f),
                        tint = toastMessage.toastType.primaryColor
                    )
                }
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                HText.SingleLine(
                    text = stringResource(toastMessage.toastType.titleRes).capitalize(),
                    fontSize = 18.sp,
                    color = HTheme.colors.onBackground,
                    fontWeight = FontWeight.Medium
                )
                HText.Default(
                    text = toastMessage.message.capitalize(),
                    fontSize = 11.sp,
                    color = HTheme.colors.onBackground.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }

    /**
     * Enqueues a success notification message.
     *
     * @param message Description text of the notification.
     * @param length Display duration of the toast. Defaults to [HToastLength.LONG].
     */
    fun makeSuccess(message: String, length: HToastLength = HToastLength.LONG) =
        CoroutineScope(AppDispatchers.default).launch {
            toastChannel.send(
                HToastMessage(
                    toastType = HToastType.SUCCESS,
                    message = message,
                    length = length
                )
            )
        }

    /**
     * Enqueues an informational notification message.
     *
     * @param message Description text of the notification.
     * @param length Display duration of the toast. Defaults to [HToastLength.LONG].
     */
    fun makeInfo(message: String, length: HToastLength = HToastLength.LONG) =
        CoroutineScope(AppDispatchers.default).launch {
            toastChannel.send(
                HToastMessage(
                    toastType = HToastType.INFO,
                    message = message,
                    length = length
                )
            )
        }

    /**
     * Enqueues an error notification message.
     *
     * @param message Description text of the error. Defaults to "Something terrible happened...".
     * @param length Display duration of the toast. Defaults to [HToastLength.LONG].
     */
    fun makeError(
        message: String = "Something terrible happened...",
        length: HToastLength = HToastLength.LONG
    ) =
        CoroutineScope(AppDispatchers.default).launch {
            toastChannel.send(
                HToastMessage(
                    toastType = HToastType.ERROR,
                    message = message,
                    length = length
                )
            )
        }

    /**
     * Wraps a synchronous block execution inside a try-catch, showing an automatic
     * success toast upon completion or an error toast upon failure.
     *
     * @param successMessageRequired Controls whether a success toast is shown on success.
     * @param onError Callback executed if the action block throws an Exception.
     * @param action The block of code to run.
     */
    fun tryWithToast(
        successMessageRequired: Boolean = false,
        onError: () -> Unit = {},
        action: () -> Unit
    ) {
        try {
            action()
            if (successMessageRequired) {
                HToast.makeSuccess(message = "Операция прошла успешно")
            }
        } catch (e: Exception) {
            onError()
            HToast.makeError(message = e.message ?: "Что-то пошло не так...")
        }
    }

    /**
     * Wraps a suspend block execution inside a launch block, showing an automatic
     * success toast upon completion or an error toast upon failure.
     *
     * @param dispatcher The [CoroutineDispatcher] to run the action on. Defaults to [AppDispatchers.io].
     * @param successMessageRequired Controls whether a success toast is shown on success.
     * @param onError Callback executed if the action block throws an Exception.
     * @param action The suspend block of code to run.
     */
    fun CoroutineScope.tryWithToast(
        dispatcher: CoroutineDispatcher = AppDispatchers.io,
        successMessageRequired: Boolean = false,
        onError: () -> Unit = {},
        action: suspend () -> Unit
    ) =
        this.launch(dispatcher) {
            try {
                action()
                if (successMessageRequired)
                    HToast.makeSuccess(message = "Операция прошла успешно")
            } catch (e: Exception) {
                onError()
                Log.e("TOAST", e.stackTrace.joinToString("\n"))
                HToast.makeError(message = e.message ?: "Что-то пошло не так...")
            }
        }
}