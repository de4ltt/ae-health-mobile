package feo.health.ui.component

import android.content.Context
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
import feo.health.ui.resource.HIcons
import feo.health.ui.resource.HStrings
import feo.health.ui.resource.HStrings.capitalize
import feo.health.ui.theme.HColorScheme
import feo.health.ui.theme.HTheme
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data object HToast {

    private val toastChannel: Channel<HToastMessage> = Channel(
        capacity = 5,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private data class HToastMessage(
        val toastType: HToastType,
        val message: String,
        val length: HToastLength
    )

    enum class HToastLength(val lengthMillis: Long) {
        SHORT(1500L),
        MEDIUM(3000L),
        LONG(5000L)
    }

    private data class HToastType(
        val primaryColor: Color,
        val secondaryColor: Color,
        @param:StringRes val titleRes: Int,
        val icon: HIcons
    ) {
        companion object {
            val SUCCESS
                get() = HToastType(
                    primaryColor = HColorScheme.Additional.GREEN,
                    secondaryColor = HColorScheme.Additional.GREEN.copy(alpha = 0.2f),
                    titleRes = HStrings.successRes,
                    icon = HIcons.SUCCESS_CIRCLE
                )
            val ERROR
                get() = HToastType(
                    primaryColor = HColorScheme.Additional.RED,
                    secondaryColor = HColorScheme.Additional.RED.copy(alpha = 0.2f),
                    titleRes = HStrings.oopsRes,
                    icon = HIcons.ATTENTION
                )
            val INFO
                get() = HToastType(
                    primaryColor = HColorScheme.Additional.BLUE,
                    secondaryColor = HColorScheme.Additional.BLUE.copy(alpha = 0.2f),
                    titleRes = HStrings.veryImportant,
                    icon = HIcons.INFORMATION_CIRCLE
                )
        }
    }

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

    fun makeSuccess(message: String, length: HToastLength = HToastLength.LONG) =
        CoroutineScope(Dispatchers.Default).launch {
            toastChannel.send(
                HToastMessage(
                    toastType = HToastType.SUCCESS,
                    message = message,
                    length = length
                )
            )
        }

    fun makeInfo(message: String, length: HToastLength = HToastLength.LONG) =
        CoroutineScope(Dispatchers.Default).launch {
            toastChannel.send(
                HToastMessage(
                    toastType = HToastType.INFO,
                    message = message,
                    length = length
                )
            )
        }

    fun makeError(
        message: String = "Something terrible happened...",
        length: HToastLength = HToastLength.LONG
    ) =
        CoroutineScope(Dispatchers.Default).launch {
            toastChannel.send(
                HToastMessage(
                    toastType = HToastType.ERROR,
                    message = message,
                    length = length
                )
            )
        }

    fun tryWithToast(
        successMessageRequired: Boolean = false,
        onError: () -> Unit = {},
        action: () -> Unit
    ) = try {
        if (successMessageRequired)
            action()
        HToast.makeSuccess(message = "Операция прошла успешно")
    } catch (e: Exception) {
        onError
        HToast.makeError(message = e.message ?: "Что-то пошло не так...")
    }

    fun CoroutineScope.tryWithToast(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
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