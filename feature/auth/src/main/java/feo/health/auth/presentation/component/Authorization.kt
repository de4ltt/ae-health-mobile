package feo.health.auth.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.valentinilk.shimmer.shimmer
import feo.health.auth.presentation.viewmodel.companion.AuthEvent
import feo.health.auth.presentation.viewmodel.companion.AuthFieldsState
import feo.health.auth.presentation.viewmodel.companion.AuthState
import feo.health.ui.component.HProgressIndicator.Shimmer.defaultShimmer
import feo.health.ui.component.HText
import feo.health.ui.component.HTextBar
import feo.health.ui.component.HToast
import feo.health.ui.component.container.HContainer
import feo.health.ui.component.container.HList
import feo.health.ui.resource.HIcons
import feo.health.ui.resource.HStrings
import feo.health.ui.resource.HStrings.capitalize
import feo.health.ui.theme.HColorScheme
import feo.health.ui.theme.HTheme
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.time.ExperimentalTime

private object PasswordOutputTransformation : OutputTransformation {
    private const val mask = '•'

    override fun TextFieldBuffer.transformOutput() {
        for (i in 0 until length) {
            replace(i, i + 1, mask.toString())
        }
    }
}


object Authorization {

    private enum class SignInFields(
        val hintRes: Int,
        val icon: HIcons,
        val onInput: (String) -> Unit,
        val outputTransformation: OutputTransformation? = null,
    ) {
        EMAIL(
            icon = HIcons.EMAIL,
            hintRes = HStrings.emailRes,
            onInput = {
                AuthFieldsState.SignIn.email.value = it
            }),
        PASSWORD(
            icon = HIcons.KEY,
            hintRes = HStrings.passwordRes,
            onInput = { AuthFieldsState.SignIn.password.value = it },
            outputTransformation = PasswordOutputTransformation
        )
    }

    private enum class SignUpFields(
        val hintRes: Int,
        val icon: HIcons,
        val onInput: (String) -> Unit,
        val outputTransformation: OutputTransformation? = null,
    ) {
        NAME(
            icon = HIcons.USER,
            hintRes = HStrings.nameRes,
            onInput = {
                AuthFieldsState.SignUp.name.value = it
            }
        ),
        EMAIL(
            icon = HIcons.EMAIL,
            hintRes = HStrings.emailRes,
            onInput = { AuthFieldsState.SignUp.email.value = it }),
        PASSWORD(
            icon = HIcons.KEY,
            hintRes = HStrings.passwordRes,
            onInput = { AuthFieldsState.SignUp.password.value = it },
            outputTransformation = PasswordOutputTransformation
        ),
        DATE_OF_BIRTH(
            icon = HIcons.BIRTHDAY,
            hintRes = HStrings.dateOfBirth,
            onInput = { AuthFieldsState.SignUp.dateOfBirth.value = it });
    }

    @Composable
    fun SignInScreen(
        modifier: Modifier = Modifier,
        screenState: StateFlow<AuthState>,
        onEvent: (AuthEvent) -> Unit,
    ) = HContainer.Default(
        modifier = modifier.fillMaxSize(),
        backgroundColor = HColorScheme.Additional.TRANSPARENT
    ) {

        val screenState by screenState.collectAsStateWithLifecycle()

        val cannotGoBack = HStrings.cannotGoBack.capitalize()

        HList.DefaultTitled(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center,
            title = HStrings.signIn.capitalize(),
            items = SignInFields.entries,
            contentPadding = PaddingValues(vertical = 5.dp),
            itemContainer = {
                HTextBar.Items(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .clip(HTheme.shapes.rounded12)
                        .background(HTheme.colors.onBackgroundContainer)
                        .then(
                            if (screenState is AuthState.SignIn.Loading)
                                Modifier.shimmer(defaultShimmer)
                            else Modifier
                        ),
                    contentModifier = Modifier.padding(15.dp),
                    outputTransformation = it.outputTransformation,
                    textStyle = HTheme.typography.medium,
                    onInput = it.onInput,
                    enabled = screenState !is AuthState.SignIn.Loading,
                    lineLimits = TextFieldLineLimits.SingleLine,
                    hintText = stringResource(it.hintRes).capitalize(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    frontItem = { it.icon() }
                )
            }
        )
        HText.SingleLine(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        if (screenState is AuthState.SignIn.Loading)
                            HToast.makeInfo(cannotGoBack)
                        onEvent(AuthEvent.OnSwitchScreen)
                    },
                    indication = null,
                    interactionSource = null
                ),
            text = HStrings.signUp.capitalize(),
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline,
            color = HTheme.colors.onBackground.copy(alpha = 0.5f)
        )
        Spacer(Modifier.height(30.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            HContainer.Default(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .wrapContentHeight()
                    .clickable(
                        onClick = { onEvent(AuthEvent.OnSignIn) },
                        indication = null,
                        interactionSource = null
                    ),
                backgroundColor = HTheme.colors.primary
            ) {
                HText.SingleLine(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    text = HStrings.procede.capitalize(),
                    fontWeight = FontWeight.SemiBold,
                    color = HTheme.colors.background
                )
            }
        }
    }

    @OptIn(ExperimentalTime::class)
    @Composable
    fun SignUpScreen(
        modifier: Modifier = Modifier,
        screenState: StateFlow<AuthState>,
        onEvent: (AuthEvent) -> Unit
    ) {

        val screenState by screenState.collectAsStateWithLifecycle()
        val cannotGoBack = HStrings.cannotGoBack.capitalize()

        HContainer.Default(
            modifier = modifier.fillMaxSize(),
            backgroundColor = HColorScheme.Additional.TRANSPARENT,
        ) {
            HList.DefaultTitled(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                title = HStrings.signUp.capitalize(),
                items = SignUpFields.entries,
                contentPadding = PaddingValues(vertical = 5.dp),
                itemContainer = {
                    if (it == SignUpFields.DATE_OF_BIRTH) {

                        val state = rememberTextFieldState()
                        var isDatePickerShown by remember {
                            mutableStateOf(false)
                        }

                        if (isDatePickerShown)
                            DatePickerModal(
                                onDateSelected = { date ->
                                    date.let {
                                        val date = Date(date ?: System.currentTimeMillis())
                                        val formattedDate = SimpleDateFormat(
                                            "dd.MM.yyyy",
                                            Locale.getDefault()
                                        ).format(date)
                                        state.setTextAndPlaceCursorAtEnd(formattedDate)
                                        AuthFieldsState.SignUp.dateOfBirth.value = formattedDate
                                    }
                                },
                                onDismiss = { isDatePickerShown = false }
                            )

                        HTextBar.SingleLineWithFrontIcon(
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .clip(HTheme.shapes.rounded12)
                                .background(HTheme.colors.onBackgroundContainer)
                                .clickable(
                                    enabled = screenState !is AuthState.SignUp.Loading,
                                    onClick = { isDatePickerShown = true },
                                    interactionSource = null,
                                    indication = null
                                )
                                .then(
                                    if (screenState is AuthState.SignUp.Loading)
                                        Modifier.shimmer(defaultShimmer)
                                    else Modifier
                                ),
                            contentModifier = Modifier.padding(15.dp),
                            textStyle = HTheme.typography.medium,
                            state = state,
                            hintText = stringResource(it.hintRes).capitalize(),
                            frontItem = { it.icon() }
                        )
                    } else {
                        HTextBar.Items(
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .clip(HTheme.shapes.rounded12)
                                .background(HTheme.colors.onBackgroundContainer)
                                .then(
                                    if (screenState is AuthState.SignUp.Loading)
                                        Modifier.shimmer(defaultShimmer)
                                    else Modifier
                                ),
                            contentModifier = Modifier.padding(15.dp),
                            outputTransformation = it.outputTransformation,
                            onInput = it.onInput,
                            textStyle = HTheme.typography.medium,
                            lineLimits = TextFieldLineLimits.SingleLine,
                            hintText = stringResource(it.hintRes).capitalize(),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                            frontItem = { it.icon() }
                        )
                    }
                }
            )
            HText.SingleLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            if (screenState is AuthState.SignUp.Loading)
                                HToast.makeInfo(cannotGoBack)
                            onEvent(AuthEvent.OnSwitchScreen)
                        },
                        indication = null,
                        interactionSource = null
                    ),
                text = HStrings.signIn.capitalize(),
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
                color = HTheme.colors.onBackground.copy(alpha = 0.5f)
            )
            Spacer(Modifier.height(30.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                HContainer.Default(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentHeight()
                        .clickable(
                            onClick = { onEvent(AuthEvent.OnSignUp) },
                            indication = null,
                            interactionSource = null
                        ),
                    backgroundColor = HTheme.colors.primary
                ) {
                    HText.SingleLine(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        text = HStrings.procede.capitalize(),
                        fontWeight = FontWeight.SemiBold,
                        color = HTheme.colors.background
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun DatePickerModal(
        onDateSelected: (Long?) -> Unit,
        onDismiss: () -> Unit
    ) {
        val datePickerState = rememberDatePickerState(
            selectableDates = object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis < System.currentTimeMillis()
                }
            }
        )

        DatePickerDialog(
            onDismissRequest = onDismiss,
            colors = DatePickerDefaults.colors().copy(
                containerColor = HTheme.colors.onBackgroundContainer
            ),
            confirmButton = {
                TextButton(
                    onClick = {
                        onDateSelected(datePickerState.selectedDateMillis)
                        onDismiss()
                    },
                    colors = ButtonDefaults.textButtonColors()
                        .copy(
                            contentColor = HTheme.colors.primary
                        )
                ) {
                    Text(HStrings.ok.uppercase())
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss,
                    colors = ButtonDefaults.textButtonColors()
                        .copy(contentColor = HTheme.colors.primary)
                ) {
                    Text(HStrings.cancel.uppercase())
                }
            }
        ) {
            DatePicker(
                state = datePickerState, colors = DatePickerDefaults.colors().copy(
                    containerColor = HTheme.colors.onBackgroundContainer,
                    selectedDayContentColor = HTheme.colors.background,
                    navigationContentColor = HTheme.colors.primary,
                    selectedYearContainerColor = HTheme.colors.primary,
                    dateTextFieldColors = TextFieldDefaults.colors().copy(),
                    todayDateBorderColor = HTheme.colors.primary,
                    todayContentColor = HTheme.colors.primary,
                    selectedDayContainerColor = HTheme.colors.primary
                )
            )
        }
    }
}