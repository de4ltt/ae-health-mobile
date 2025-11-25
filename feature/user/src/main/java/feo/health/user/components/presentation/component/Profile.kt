package feo.health.user.components.presentation.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.valentinilk.shimmer.shimmer
import feo.health.ui.component.HProgressIndicator.Shimmer.defaultShimmer
import feo.health.ui.component.HText
import feo.health.ui.component.HTextBar
import feo.health.ui.component.container.HContainer
import feo.health.ui.navigation.Routes
import feo.health.ui.resource.HIcons
import feo.health.ui.resource.HStrings
import feo.health.ui.resource.HStrings.capitalize
import feo.health.ui.theme.HColorScheme
import feo.health.ui.theme.HTheme
import feo.health.ui.theme.fontFamily
import feo.health.ui.util.Validator
import feo.health.user.components.presentation.component.Profile.InfoCardState.Companion.inverseState
import feo.health.user.components.presentation.model.ChangePassword
import feo.health.user.components.presentation.model.User
import feo.health.user.components.presentation.viewmodel.companion.UserEvent
import feo.health.user.components.presentation.viewmodel.companion.UserState
import kotlinx.coroutines.flow.StateFlow

object Profile {

    @Composable
    fun Screen(
        state: StateFlow<UserState>,
        navHostController: NavHostController,
        onEvent: (UserEvent) -> Unit
    ) = HContainer.TitledScreen(
        modifier = Modifier.fillMaxSize(),
        title = HStrings.profile.capitalize()
    ) {

        val state by state.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            onEvent(UserEvent.Profile.OnRefresh)
        }

        Column {
            when (state) {
                UserState.Profile.Default -> ProfileScreenInfoCards(
                    user = UserState.Profile.Default.user!!,
                    onEvent = onEvent,
                    onLogOut = { navHostController.navigate(Routes.authLogOut) }
                )

                UserState.Profile.Loading -> Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    repeat(5) { ShimmerInfoCard() }
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 2.dp,
                        color = HTheme.colors.onBackgroundContainer
                    )
                    repeat(4) { ShimmerInfoCard() }
                }

                else -> {}
            }
        }
    }

    @Composable
    private fun ProfileScreenInfoCards(
        user: User,
        onLogOut: () -> Unit,
        onEvent: (UserEvent) -> Unit
    ) {

        val context = LocalContext.current

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ProfileInfoCard(
                subtitle = stringResource(HStrings.nameRes).capitalize(),
                icon = HIcons.USER,
                title = user.name,
                onValueChange = {
                    Validator.validate(
                        context = context,
                        text = it,
                        type = Validator.FieldType.NAME
                    ).let { valid ->
                        if (valid)
                            onEvent(
                                UserEvent.Profile.OnUpdateUserData(
                                    user.copy(
                                        name = it
                                    )
                                )
                            )
                    }
                }
            )

            ProfileInfoCard(
                subtitle = stringResource(HStrings.emailRes).capitalize(),
                icon = HIcons.EMAIL,
                title = user.email,
                keyboardType = KeyboardType.Email,
                onValueChange = {
                    Validator.validate(
                        context = context,
                        text = it,
                        type = Validator.FieldType.EMAIL
                    ).let { valid ->
                        onEvent(
                            UserEvent.Profile.OnUpdateUserData(
                                user.copy(
                                    email = it
                                )
                            )
                        )
                    }
                }
            )


            ProfileInfoCard(
                subtitle = stringResource(HStrings.weightRes).capitalize(),
                icon = HIcons.WEIGHT,
                title = user.weightKg?.toString() ?: HStrings.notSet.capitalize(),
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    Validator.validate(
                        context = context,
                        text = it,
                        type = Validator.FieldType.WEIGHT
                    ).let { valid ->
                        onEvent(
                            UserEvent.Profile.OnUpdateUserData(
                                user.copy(
                                    weightKg = it.toFloat()
                                )
                            )
                        )
                    }
                }
            )

            ProfileInfoCard(
                subtitle = stringResource(HStrings.heightRes).capitalize(),
                icon = HIcons.ARROW_UP,
                title = user.height?.toString(),
                keyboardType = KeyboardType.Decimal,
                onValueChange = {
                    Validator.validate(
                        context = context,
                        text = it,
                        type = Validator.FieldType.HEIGHT
                    ).let { valid ->
                        onEvent(
                            UserEvent.Profile.OnUpdateUserData(
                                user.copy(
                                    height = it.toInt()
                                )
                            )
                        )
                    }
                }
            )

            ChangePasswordCard(
                onPasswordChange = {
                    (Validator.validate(
                        context = context,
                        text = it.oldPassword,
                        type = Validator.FieldType.PASSWORD
                    ) && Validator.validate(
                        context = context,
                        text = it.newPassword,
                        type = Validator.FieldType.PASSWORD
                    )).let { valid ->
                        if (valid)
                            onEvent(UserEvent.Profile.OnChangePassword(it))
                    }
                }
            )

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = HTheme.colors.onBackgroundContainer
            )

            ProfileItemCard(
                title = HStrings.history.capitalize(),
                iconBackgroundTint = HTheme.colors.secondary,
                iconTint = HTheme.colors.background,
                onClick = { onEvent(UserEvent.History.OnRefresh) },
                icon = HIcons.CLOCK
            )

            ProfileItemCard(
                title = HStrings.favourites.capitalize(),
                iconBackgroundTint = HTheme.colors.secondary,
                iconTint = HTheme.colors.background,
                onClick = { onEvent(UserEvent.Favourites.OnRefresh) },
                icon = HIcons.HEART
            )

            ProfileItemCard(
                title = HStrings.logOut.capitalize(),
                iconBackgroundTint = HTheme.colors.onBackgroundContainer,
                iconTint = HTheme.colors.onBackground,
                onClick = {
                    onEvent(UserEvent.Profile.OnLogOut)
                    onLogOut()
                },
                icon = HIcons.ARROW_LEFT
            )

            ProfileItemCard(
                title = HStrings.deleteAccount.capitalize(),
                iconBackgroundTint = HColorScheme.Additional.RED,
                iconTint = HTheme.colors.background,
                onClick = { onEvent(UserEvent.Profile.OnDeleteUser) },
                icon = HIcons.BIN
            )
        }
    }

    @OptIn(ExperimentalSharedTransitionApi::class)
    @Composable
    private fun ProfileInfoCard(
        modifier: Modifier = Modifier,
        title: String? = null,
        subtitle: String? = null,
        icon: HIcons,
        hintText: String = HStrings.notSet.capitalize(),
        keyboardType: KeyboardType = KeyboardType.Text,
        iconBackgroundTint: Color = HTheme.colors.onBackgroundContainer,
        titleColor: Color = HTheme.colors.onBackground,
        subtitleColor: Color = HTheme.colors.secondary,
        onValueChange: (String) -> Unit = {}
    ) {

        var infoCardState by remember { mutableStateOf(InfoCardState.DISPLAY) }

        val iconTint by animateColorAsState(
            if (infoCardState == InfoCardState.DISPLAY) HTheme.colors.onBackground
            else HTheme.colors.primary
        )

        val iconKey = "icon"
        val containerKey = "container"
        val textKey = "text"
        SharedTransitionLayout(
            modifier = Modifier.clickable(
                onClick = { infoCardState = infoCardState.inverseState() },
                interactionSource = null,
                indication = null
            )
        ) {
            val textContentState = rememberSharedContentState(textKey)
            val containerContentState = rememberSharedContentState(containerKey)
            val iconContentState = rememberSharedContentState(iconKey)
            AnimatedContent(targetState = infoCardState, transitionSpec = {
                (expandHorizontally(
                    expandFrom = Alignment.Start,
                    initialWidth = { fullWidth -> fullWidth / 5 })).togetherWith(
                    shrinkHorizontally(shrinkTowards = Alignment.Start)
                )
            }) { targetState ->
                when (targetState) {
                    InfoCardState.EDIT -> {
                        val textColor = HTheme.colors.secondary

                        val state = rememberTextFieldState(initialText = title ?: "")

                        BasicTextField(
                            state = state,
                            modifier = modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clip(HTheme.shapes.rounded12)
                                .background(HTheme.colors.onBackgroundContainer)
                                .sharedBounds(
                                    sharedContentState = containerContentState,
                                    animatedVisibilityScope = this@AnimatedContent,
                                    clipInOverlayDuringTransition = OverlayClip(HTheme.shapes.rounded12)
                                ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = keyboardType,
                                autoCorrectEnabled = true
                            ),
                            textStyle = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = HTheme.typography.medium.fontWeight,
                                fontFamily = fontFamily
                            ),
                            lineLimits = TextFieldLineLimits.SingleLine,
                            decorator = { innerTextField ->
                                Row(
                                    modifier = Modifier.padding(15.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    icon.invoke(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .sharedElement(
                                                sharedContentState = iconContentState,
                                                animatedVisibilityScope = this@AnimatedContent
                                            ),
                                        tint = iconTint
                                    )
                                    Box(
                                        modifier = Modifier.weight(1f),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        if (state.text.isEmpty())
                                            HText.Default(
                                                modifier = Modifier,
                                                text = hintText,
                                                color = textColor,
                                                fontSize = HTheme.typography.medium.fontSize,
                                                fontWeight = HTheme.typography.medium.fontWeight
                                            )
                                        Box(
                                            modifier = Modifier
                                                .wrapContentSize()
                                                .sharedElement(
                                                    sharedContentState = textContentState,
                                                    animatedVisibilityScope = this@AnimatedContent
                                                )
                                        ) { innerTextField() }
                                    }
                                    HIcons.SUCCESS_CIRCLE(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clickable(
                                                onClick = {
                                                    onValueChange(state.text.toString())
                                                    infoCardState = infoCardState.inverseState()
                                                },
                                                interactionSource = null,
                                                indication = null
                                            )
                                    )
                                }
                            }
                        )
                    }

                    InfoCardState.DISPLAY -> Row(
                        modifier = modifier.height(IntrinsicSize.Max),
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        HContainer.Default(
                            backgroundColor = iconBackgroundTint,
                            modifier = Modifier
                                .fillMaxHeight()
                                .wrapContentWidth()
                                .sharedBounds(
                                    sharedContentState = containerContentState,
                                    animatedVisibilityScope = this@AnimatedContent,
                                    clipInOverlayDuringTransition = OverlayClip(HTheme.shapes.rounded12)
                                ),
                            content = {
                                icon.invoke(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .sharedElement(
                                            sharedContentState = iconContentState,
                                            animatedVisibilityScope = this@AnimatedContent
                                        ),
                                    tint = iconTint
                                )
                            }
                        )

                        Column(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(vertical = 5.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            HText.BasicMarquee(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .sharedElement(
                                        sharedContentState = textContentState,
                                        animatedVisibilityScope = this@AnimatedContent
                                    ),
                                text = title ?: hintText,
                                color = titleColor,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal
                            )

                            subtitle?.let {
                                HText.Default(
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .animateEnterExit(
                                            enter = fadeIn(tween(delayMillis = 300))
                                        ),
                                    text = subtitle,
                                    color = subtitleColor,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun ProfileItemCard(
        modifier: Modifier = Modifier,
        title: String? = null,
        subtitle: String? = null,
        icon: HIcons,
        iconTint: Color = HTheme.colors.onBackground,
        hintText: String = HStrings.notSet.capitalize(),
        iconBackgroundTint: Color = HTheme.colors.onBackgroundContainer,
        titleColor: Color = HTheme.colors.onBackground,
        subtitleColor: Color = HTheme.colors.secondary,
        onClick: () -> Unit
    ) = Row(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = null
            ),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        HContainer.Default(
            backgroundColor = iconBackgroundTint,
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth(),
            content = {
                icon.invoke(
                    modifier = Modifier
                        .size(30.dp),
                    tint = iconTint
                )
            }
        )

        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.Start
        ) {
            HText.BasicMarquee(
                modifier = Modifier
                    .wrapContentWidth(),
                text = title ?: hintText,
                color = titleColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )

            subtitle?.let {
                HText.Default(
                    modifier = Modifier
                        .wrapContentWidth(),
                    text = subtitle,
                    color = subtitleColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }

    private enum class InfoCardState {
        EDIT,
        DISPLAY;

        companion object {
            fun InfoCardState.inverseState(): InfoCardState = when (this) {
                EDIT -> DISPLAY
                DISPLAY -> EDIT
            }
        }
    }

    @OptIn(ExperimentalSharedTransitionApi::class)
    @Composable
    private fun ChangePasswordCard(
        modifier: Modifier = Modifier,
        onPasswordChange: (ChangePassword) -> Unit
    ) {

        var state by remember { mutableStateOf(InfoCardState.DISPLAY) }

        SharedTransitionLayout(
            modifier = modifier.clickable(
                onClick = { state = state.inverseState() },
                indication = null,
                interactionSource = null
            )
        ) {
            val sharedContainer = rememberSharedContentState("pContainer")
            val sharedIcon = rememberSharedContentState("pIcon")
            val sharedText = rememberSharedContentState("pText")
            AnimatedContent(
                targetState = state,
                transitionSpec = {
                    (expandIn(expandFrom = Alignment.TopStart)).togetherWith(
                        shrinkOut(shrinkTowards = Alignment.TopStart)
                    )
                }
            ) {
                when (it) {
                    InfoCardState.EDIT -> HContainer.Default(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .sharedBounds(
                                sharedContentState = sharedContainer,
                                animatedVisibilityScope = this@AnimatedContent,
                                clipInOverlayDuringTransition = OverlayClip(HTheme.shapes.rounded12)
                            ),
                        backgroundColor = HTheme.colors.primary,
                    ) {

                        val oldPasswordState = rememberTextFieldState()
                        val newPasswordState = rememberTextFieldState()

                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Max),
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            HContainer.Default(
                                backgroundColor = HTheme.colors.background,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .wrapContentWidth()
                                    .animateEnterExit(
                                        enter = scaleIn(),
                                        exit = scaleOut()
                                    ),
                                content = {
                                    HIcons.KEY.invoke(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .sharedElement(
                                                sharedContentState = sharedIcon,
                                                animatedVisibilityScope = this@AnimatedContent
                                            ),
                                        tint = HTheme.colors.primary
                                    )
                                }
                            )

                            HText.BasicMarquee(
                                modifier = Modifier
                                    .weight(1f)
                                    .sharedElement(
                                        sharedContentState = sharedText,
                                        animatedVisibilityScope = this@AnimatedContent
                                    ),
                                text = HStrings.changePassword.capitalize(),
                                color = HTheme.colors.background,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                            )

                            HIcons.SUCCESS_CIRCLE(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable(
                                        indication = null,
                                        interactionSource = null,
                                        onClick = {
                                            val changePassword = ChangePassword(
                                                oldPassword = oldPasswordState.text.toString(),
                                                newPassword = newPasswordState.text.toString()
                                            )
                                            onPasswordChange(changePassword)
                                        }
                                    ),
                                tint = HTheme.colors.background
                            )
                        }

                        HTextBar.Default(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .background(HTheme.colors.background, HTheme.shapes.rounded12),
                            contentModifier = Modifier.padding(15.dp),
                            hintText = HStrings.oldPassword.capitalize(),
                            state = oldPasswordState
                        )
                        HTextBar.Default(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .background(HTheme.colors.background, HTheme.shapes.rounded12),
                            contentModifier = Modifier.padding(15.dp),
                            hintText = HStrings.newPassword.capitalize(),
                            state = newPasswordState
                        )
                    }

                    InfoCardState.DISPLAY -> Row(
                        modifier = modifier.height(IntrinsicSize.Max),
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        HContainer.Default(
                            backgroundColor = HTheme.colors.primary,
                            modifier = Modifier
                                .fillMaxHeight()
                                .wrapContentWidth()
                                .sharedBounds(
                                    sharedContentState = sharedContainer,
                                    animatedVisibilityScope = this@AnimatedContent,
                                    clipInOverlayDuringTransition = OverlayClip(HTheme.shapes.rounded12)
                                ),
                            content = {
                                HIcons.KEY.invoke(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .sharedElement(
                                            sharedContentState = sharedIcon,
                                            animatedVisibilityScope = this@AnimatedContent
                                        ),
                                    tint = HTheme.colors.background
                                )
                            }
                        )

                        HText.BasicMarquee(
                            modifier = Modifier
                                .wrapContentWidth()
                                .sharedElement(
                                    sharedContentState = sharedText,
                                    animatedVisibilityScope = this@AnimatedContent
                                ),
                            text = HStrings.changePassword.capitalize(),
                            color = HTheme.colors.onBackground,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }

                }
            }
        }
    }

    @Composable
    private fun ShimmerInfoCard(modifier: Modifier = Modifier) = Row(
        modifier = modifier.height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        HContainer.Default(
            backgroundColor = HTheme.colors.primary,
            modifier = Modifier
                .height(60.dp)
                .aspectRatio(1f)
                .shimmer(defaultShimmer),
            paddingValues = PaddingValues.Zero
        )

        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.Start
        ) {
            val title = with(LocalDensity.current) { 18.sp.toDp() }
            val subtitle = with(LocalDensity.current) { 15.sp.toDp() }

            HContainer.Default(
                backgroundColor = HTheme.colors.primary,
                modifier = Modifier
                    .height(title)
                    .fillMaxWidth(0.25f)
                    .shimmer(defaultShimmer),
                paddingValues = PaddingValues.Zero,
                shape = HTheme.shapes.rectangular
            )

            HContainer.Default(
                backgroundColor = HTheme.colors.primary,
                modifier = Modifier
                    .height(subtitle)
                    .fillMaxWidth(0.8f)
                    .shimmer(defaultShimmer),
                paddingValues = PaddingValues.Zero,
                shape = HTheme.shapes.rectangular
            )
        }
    }
}