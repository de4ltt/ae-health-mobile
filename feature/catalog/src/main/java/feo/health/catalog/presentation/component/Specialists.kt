package feo.health.catalog.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.toUri
import com.valentinilk.shimmer.shimmer
import feo.health.catalog.presentation.viewmodel.companion.CatalogEvent
import feo.health.catalog.presentation.model.ICatalog
import feo.health.catalog.presentation.model.Review
import feo.health.catalog.presentation.model.Review.Companion.ReviewIndication.Companion.defineIndication
import feo.health.catalog.presentation.util.Mock
import feo.health.ui.component.HProgressIndicator
import feo.health.ui.component.HProgressIndicator.Shimmer.defaultShimmer
import feo.health.ui.component.HText
import feo.health.ui.component.container.HContainer
import feo.health.ui.component.container.HList
import feo.health.ui.resource.HIcons
import feo.health.ui.resource.HStrings
import feo.health.ui.resource.HStrings.capitalize
import feo.health.ui.theme.HTheme
import feo.health.ui.theme.fontFamily
import feo.health.ui.util.ILoading
import java.time.format.DateTimeFormatter

object Specialists {

    object Profile : ILoading {
        @Composable
        fun Screen(
            modifier: Modifier = Modifier,
            specialist: ICatalog.Doctor
        ) = HContainer.TitledScreen(
            modifier = Modifier.fillMaxSize(),
            title = HStrings.specialistProfile.capitalize(),
        ) {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val sFullName = specialist.getFullName()

                    HContainer.Image.AsyncImage(
                        modifier = Modifier
                            .clip(HTheme.shapes.rounded12)
                            .fillMaxWidth(0.25f)
                            .aspectRatio(1f),
                        imageModifier = Modifier.fillMaxSize(),
                        progressIndicator = HProgressIndicator.Shimmer,
                        onError = { HIcons.HOME() },
                        model = specialist.imageUri?.toUri()
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        HText.BasicMarquee(
                            text = sFullName.surname,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = HTheme.colors.onBackground
                        )
                        HText.BasicMarquee(
                            text = "${sFullName.name}${sFullName.patronymic?.let { " $it" }}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = HTheme.colors.secondary
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        specialist.experience?.let {
                            val expString = listOf(
                                HStrings.experience.capitalize(), it.toString(), when {
                                    it % 10 == 1 -> HStrings.expOneYr
                                    it % 10 in 2..4 ->
                                        if (it > 4) HStrings.expTwoFourMoreYrs else HStrings.expTwoFourLessYrs

                                    else -> HStrings.expManyYrs
                                }
                            ).joinToString(" ")
                            HText.SingleLine(
                                text = expString,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = HTheme.colors.primary
                            )
                        }
                    }
                }

                specialist.specialities?.let {
                    val specialitiesDotted = it.joinToString(" · ") { el -> el.name.capitalize() }
                    HText.Default(
                        modifier = Modifier.padding(vertical = 6.dp),
                        text = specialitiesDotted,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }

                specialist.reviews?.let { spec ->
                    HList.LazyTitled(
                        modifier = Modifier.fillMaxWidth(),
                        title = HStrings.reviews.capitalize(),
                        contentPadding = PaddingValues(bottom = 15.dp),
                        fontSize = 22.sp,
                        items = spec,
                        itemContainer = {
                            ReviewCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                title = HStrings.appointmentWas.capitalize() + " ${
                                    it.date?.format(
                                        DateTimeFormatter.ofPattern("dd.MM.yyyy")
                                    )
                                }",
                                description = it.text,
                                bottomColor = it.rating?.let { rat -> defineIndication(rat) }?.color
                                    ?: HTheme.colors.secondary,
                                bottomIcon = it.rating?.let { HIcons.STAR },
                                bottomText = it.rating?.toString()
                            )
                        }
                    )
                }
            }
        }

        @Composable
        private fun ReviewCard(
            modifier: Modifier = Modifier,
            description: String? = null,
            descriptionColor: Color = HTheme.colors.onBackground,
            titleColor: Color = HTheme.colors.primary,
            bottomIcon: HIcons? = null,
            bottomText: String? = null,
            bottomColor: Color = HTheme.colors.secondary,
            title: String
        ) {
            HContainer.Default(
                modifier = modifier
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    HText.SingleLine(
                        text = title,
                        color = titleColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    description?.let {
                        if (it.isNotEmpty())
                            HText.Default(
                                text = description,
                                color = descriptionColor,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        bottomIcon?.invoke(tint = bottomColor, modifier = Modifier.size(20.dp))
                        bottomText?.let {
                            HText.SingleLine(
                                text = bottomText,
                                color = bottomColor, fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }

        @Composable
        fun ShimmerReviewCard(
            modifier: Modifier = Modifier,
        ) {
            HContainer.Default(
                modifier = modifier.shimmer(defaultShimmer)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    val titleSize = with(LocalDensity.current) { 16.sp.toDp() }
                    val subtitleSize = with(LocalDensity.current) { 14.sp.toDp() }

                    HContainer.Default(
                        modifier = Modifier
                            .height(titleSize)
                            .fillMaxWidth(0.8f),
                        shape = HTheme.shapes.rectangular,
                        backgroundColor = HTheme.colors.primary
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        repeat(2) {
                            HContainer.Default(
                                modifier = Modifier
                                    .height(subtitleSize)
                                    .fillMaxWidth(),
                                shape = HTheme.shapes.rectangular,
                                backgroundColor = HTheme.colors.primary
                            )
                        }
                        HContainer.Default(
                            modifier = Modifier
                                .height(subtitleSize)
                                .fillMaxWidth(0.25f),
                            shape = HTheme.shapes.rectangular,
                            backgroundColor = HTheme.colors.primary
                        )
                    }
                    HContainer.Default(
                        modifier = Modifier
                            .height(20.dp)
                            .fillMaxWidth(0.25f),
                        shape = HTheme.shapes.rectangular,
                        backgroundColor = HTheme.colors.primary
                    )
                }
            }
        }

        @Composable
        override fun LoadingScreen(vararg params: Any) = HContainer.TitledScreen(
            modifier = Modifier.fillMaxSize(),
            title = HStrings.specialistProfile.capitalize(),
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .shimmer(defaultShimmer),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HContainer.Default(
                        modifier = Modifier
                            .clip(HTheme.shapes.rounded12)
                            .fillMaxWidth(0.25f)
                            .aspectRatio(1f),
                        paddingValues = PaddingValues.Zero,
                        backgroundColor = HTheme.colors.primary
                    )
                    val title = with(LocalDensity.current) { 18.sp.toDp() }
                    val subtitle = with(LocalDensity.current) { 16.sp.toDp() }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        HContainer.Default(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(title),
                            paddingValues = PaddingValues.Zero,
                            shape = HTheme.shapes.rectangular,
                            backgroundColor = HTheme.colors.primary
                        )
                        HContainer.Default(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(subtitle),
                            paddingValues = PaddingValues.Zero,
                            shape = HTheme.shapes.rectangular,
                            backgroundColor = HTheme.colors.primary
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        HContainer.Default(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(subtitle),
                            paddingValues = PaddingValues.Zero,
                            shape = HTheme.shapes.rectangular,
                            backgroundColor = HTheme.colors.primary
                        )
                    }
                }


                val specialities = with(LocalDensity.current) { 14.sp.toDp() }
                HContainer.Default(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(specialities)
                        .shimmer(defaultShimmer),
                    paddingValues = PaddingValues.Zero,
                    shape = HTheme.shapes.rectangular,
                    backgroundColor = HTheme.colors.primary
                )

                HList.LazyTitled(
                    modifier = Modifier.fillMaxWidth(),
                    title = HStrings.reviews.capitalize(),
                    scrollingEnabled = false,
                    contentPadding = PaddingValues(bottom = 15.dp),
                    fontSize = 22.sp,
                    items = Mock.specialists,
                    itemContainer = {
                        ShimmerReviewCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                        )
                    }
                )
            }
        }
    }

    object Items : ILoading {

        @Composable
        fun Screen(
            modifier: Modifier = Modifier,
            specialists: List<ICatalog.Doctor>,
            onClick: (CatalogEvent) -> Unit
        ) = HContainer.TitledScreen(
            modifier = Modifier.fillMaxSize(),
            title = HStrings.specialists.capitalize()
        ) {
            HList.Lazy(
                modifier = modifier,
                contentPadding = PaddingValues(bottom = 10.dp),
                items = specialists,
                itemContainer = {
                    HContainer.Default(
                        contentAlignment = Alignment.CenterStart,
                        paddingValues = PaddingValues(20.dp)
                    ) {
                        Card(specialist = it) {
                            onClick(
                                CatalogEvent.ItemInfoEvent.OnDetails(item = it.getCatalogItem())
                            )
                        }
                    }
                }
            )
        }

        @Composable
        private fun Card(
            modifier: Modifier = Modifier,
            specialist: ICatalog.Doctor,
            onClick: () -> Unit
        ) = Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.Start
        ) {

            val sFullName = specialist.getFullName()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable(
                        onClick = onClick,
                        interactionSource = null,
                        indication = null
                    ),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HContainer.Image.AsyncImage(
                    modifier = Modifier
                        .clip(HTheme.shapes.rounded12)
                        .fillMaxWidth(0.225f)
                        .aspectRatio(1f),
                    imageModifier = Modifier.fillMaxSize(),
                    progressIndicator = HProgressIndicator.Shimmer,
                    onError = { HIcons.DOCTOR() },
                    model = specialist.imageUri?.toUri()
                )
                Column {
                    HText.BasicMarquee(
                        text = sFullName.surname,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = HTheme.colors.onBackground
                    )
                    HText.BasicMarquee(
                        text = "${sFullName.name}${sFullName.patronymic?.let { " $it" }}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = HTheme.colors.secondary
                    )
                }
            }

            specialist.specialities?.let {
                val specialitiesDotted = it.joinToString(" · ") { el -> el.name.capitalize() }
                HText.Default(
                    modifier = Modifier.padding(vertical = 6.dp),
                    text = specialitiesDotted,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )
            }

            specialist.experience?.let {
                val expString = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontFamily,
                            color = HTheme.colors.onBackground
                        )
                    ) {
                        append("${HStrings.experience.capitalize()}: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = fontFamily,
                            color = HTheme.colors.onBackground
                        )
                    ) {
                        append(
                            "$it " + when {
                                it % 10 == 1 -> HStrings.expOneYr
                                it % 10 in 2..4 ->
                                    if (it > 4) HStrings.expTwoFourMoreYrs else HStrings.expTwoFourLessYrs

                                else -> HStrings.expManyYrs
                            }
                        )
                    }
                }
                HText.SingleLine(text = expString, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            specialist.rating?.let {
                val expString = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontFamily,
                            color = HTheme.colors.onBackground
                        )
                    ) { append("${HStrings.reviews.capitalize()}: ") }

                    val definition = Review.Companion.ReviewIndication.defineIndication(it)
                    withStyle(
                        style = SpanStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = fontFamily,
                            color = definition.color
                        )
                    ) { append(stringResource(definition.definition).capitalize()) }
                }
                HText.SingleLine(text = expString, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }

        @Composable
        private fun ShimmerCard(
            modifier: Modifier = Modifier,
        ) = Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HContainer.Default(
                    modifier = Modifier
                        .clip(HTheme.shapes.rounded12)
                        .fillMaxWidth(0.225f)
                        .aspectRatio(1f)
                        .shimmer(defaultShimmer),
                    backgroundColor = HTheme.colors.primary,
                    paddingValues = PaddingValues.Zero
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {

                    val title = with(LocalDensity.current) { 20.sp.toDp() }
                    val subtitle = with(LocalDensity.current) { 18.sp.toDp() }

                    HContainer.Default(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .height(title)
                            .shimmer(defaultShimmer),
                        paddingValues = PaddingValues.Zero,
                        backgroundColor = HTheme.colors.primary,
                        shape = HTheme.shapes.rectangular
                    )
                    HContainer.Default(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(subtitle)
                            .shimmer(defaultShimmer),
                        paddingValues = PaddingValues.Zero,
                        backgroundColor = HTheme.colors.primary,
                        shape = HTheme.shapes.rectangular
                    )
                }
            }

            val specialities = with(LocalDensity.current) { 14.sp.toDp() }
            HContainer.Default(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(specialities)
                    .shimmer(defaultShimmer),
                paddingValues = PaddingValues.Zero,
                backgroundColor = HTheme.colors.primary,
                shape = HTheme.shapes.rectangular
            )

            val info = with(LocalDensity.current) { 16.sp.toDp() }
            repeat(2) {
                HContainer.Default(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(info)
                        .shimmer(defaultShimmer),
                    paddingValues = PaddingValues.Zero,
                    backgroundColor = HTheme.colors.primary,
                    shape = HTheme.shapes.rectangular
                )
            }
        }

        @Composable
        override fun LoadingScreen(vararg params: Any) = HContainer.TitledScreen(
            modifier = Modifier.fillMaxSize(),
            title = HStrings.specialists.capitalize()
        ) {
            HList.Lazy(
                modifier = Modifier,
                contentPadding = PaddingValues(bottom = 10.dp),
                items = Mock.specialists,
                enabled = false,
                itemContainer = {
                    HContainer.Default(
                        contentAlignment = Alignment.CenterStart,
                        paddingValues = PaddingValues(20.dp)
                    ) {
                        ShimmerCard()
                    }
                }
            )
        }
    }
}