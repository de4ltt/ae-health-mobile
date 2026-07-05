package feo.health.catalog.presentation.component

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.valentinilk.shimmer.shimmer
import feo.health.catalog.presentation.model.Coords
import feo.health.catalog.presentation.model.ICatalog
import feo.health.catalog.presentation.model.Review
import feo.health.catalog.presentation.model.Review.Companion.ReviewIndication.Companion.defineIndication
import feo.health.catalog.presentation.viewmodel.companion.CatalogEvent
import feo.health.ui.component.HProgressIndicator
import feo.health.ui.component.HProgressIndicator.Shimmer.defaultShimmer
import feo.health.ui.component.HText
import feo.health.ui.component.HToast
import feo.health.ui.component.container.HContainer
import feo.health.ui.model.ICatalogItem
import feo.health.ui.resource.HIcons
import feo.health.ui.theme.HTheme
import feo.health.ui.util.ILoading
import feo.health.ui.util.capitalize

/**
 * Object containing UI components for displaying detailed information about organizations (Pharmacies and Clinics).
 *
 * Implements [ILoading] to provide custom loading states.
 */
object Organization : ILoading {

    /**
     * The common layout template screen for displaying organization information.
     *
     * @param modifier Modifier for the layout.
     * @param phoneNumber The contact phone number of the organization.
     * @param name The name of the organization.
     * @param website The website address of the organization.
     * @param address The physical address.
     * @param openingHours The operating schedule.
     * @param reviews The user reviews.
     * @param itemType The category type of catalog item.
     * @param link The link for specialists/services.
     * @param coords The geographical coordinates of the organization.
     * @param onEvent Event callback for parent actions.
     */
    @Composable
    private fun ItemScreen(
        modifier: Modifier = Modifier,
        phoneNumber: String? = null,
        name: String? = null,
        website: String? = null,
        address: String? = null,
        openingHours: List<String>? = null,
        reviews: List<Review>? = null,
        itemType: ICatalogItem.Companion.CatalogItemType,
        link: String? = null,
        coords: Coords? = null,
        onEvent: (CatalogEvent) -> Unit
    ) = HContainer.TitledScreen(
        modifier = Modifier.fillMaxSize(),
        title = name?.capitalize() ?: itemType.name.capitalize()
    ) {

        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            val mapUri = coords?.let { "https://static.maps.2gis.com/1.0?s=792x450&c=${it.lat},${it.lon}&z=17" }
            println(mapUri)
            HContainer.Image.AsyncImage(
                imageModifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.76f)
                    .padding(bottom = 10.dp)
                    .clip(HTheme.shapes.rounded12)
                    .background(HTheme.colors.onBackgroundContainer),
                progressIndicator = HProgressIndicator.Shimmer,
                model = mapUri?.toUri()
            )

            LazyColumn(
                modifier = modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.Start
            ) {
                val infoCardsModifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()

                address?.let {
                    item {
                        InfoCard(
                            modifier = infoCardsModifier,
                            title = stringResource(feo.health.ui.R.string.address).capitalize(),
                            subtitle = address,
                            icon = HIcons.LOCATION_MARKER
                        )
                    }
                }


                phoneNumber?.let {
                    item {
                        InfoCard(
                            modifier = infoCardsModifier,
                            title = stringResource(feo.health.ui.R.string.phone_number).capitalize(),
                            subtitle = phoneNumber,
                            icon = HIcons.PHONE
                        )
                    }

                }

                reviews?.let {
                    item {
                        val indication = defineIndication(reviews)
                        InfoCard(
                            modifier = infoCardsModifier,
                            title = stringResource(feo.health.ui.R.string.reviews).capitalize(),
                            subtitle = stringResource(indication.definition).capitalize(),
                            icon = HIcons.CHAT,
                            subtitleColor = indication.color
                        )
                    }
                }

                openingHours?.let {
                    item {
                        InfoCard(
                            modifier = infoCardsModifier,
                            title = stringResource(feo.health.ui.R.string.opening_hours).capitalize(),
                            subtitle = openingHours.joinToString("\n"),
                            icon = HIcons.CLOCK
                        )
                    }
                }

                website?.let {
                    item {
                        InfoCard(
                            modifier = infoCardsModifier,
                            title = stringResource(feo.health.ui.R.string.website).capitalize(),
                            subtitle = website,
                            icon = HIcons.WEB_SEARCH
                        )
                    }
                }

                if (itemType == ICatalogItem.Companion.CatalogItemType.CLINIC)
                    item {
                        InfoCard(
                            modifier = infoCardsModifier.clickable(
                                onClick = {
                                    link?.let {
                                        onEvent(
                                            CatalogEvent.ItemInfoEvent.OnSpecialists(
                                                type = itemType,
                                                link = link
                                            )
                                        )
                                    } ?: HToast.makeError("У объекта нет ссылки...")
                                },
                                interactionSource = null,
                                indication = null
                            ),
                            title = stringResource(feo.health.ui.R.string.specialists).capitalize(),
                            subtitle = stringResource(feo.health.ui.R.string.watch_services).capitalize(),
                            icon = HIcons.INFORMATION_CIRCLE,
                            iconTint = HTheme.colors.background,
                            iconBackgroundTint = HTheme.colors.primary
                        )
                    }
            }

            val context = LocalContext.current
            BottomButtons(
                modifier = Modifier.padding(bottom = 15.dp),
                onBack = { onEvent(CatalogEvent.OnBack) },
                onRoute = address?.let {
                    return@let {
                        val intent = Intent(Intent.ACTION_VIEW, "geo:?q=$it".toUri())
                        (context as Activity).startActivity(intent)
                    }
                },
                onPhone = phoneNumber?.let {
                    return@let {
                        val intent = Intent(Intent.ACTION_VIEW, "tel:$it".toUri())
                        (context as Activity).startActivity(intent)
                    }
                }
            )
        }
    }

    /**
     * A card component displaying pharmacy details.
     *
     * @param modifier Modifier for the layout.
     * @param pharmacy The pharmacy model object containing name, contact, etc.
     * @param onEvent Event callback for handling user interactions.
     */
    @Composable
    fun PharmacyItemCard(
        modifier: Modifier = Modifier,
        pharmacy: ICatalog.Pharmacy,
        onEvent: (CatalogEvent) -> Unit,
    ) {
        ItemScreen(
            modifier = modifier,
            name = pharmacy.name,
            phoneNumber = pharmacy.phoneNumber,
            website = pharmacy.website,
            address = pharmacy.address,
            coords = null,
            openingHours = pharmacy.openingHours,
            itemType = ICatalogItem.Companion.CatalogItemType.PHARMACY,
            onEvent = onEvent
        )
    }

    /**
     * A card component displaying clinic details.
     *
     * @param modifier Modifier for the layout.
     * @param clinic The clinic model object containing name, reviews, coords, etc.
     * @param onEvent Event callback for handling user interactions.
     */
    @Composable
    fun ClinicItemCard(
        modifier: Modifier = Modifier,
        clinic: ICatalog.Clinic,
        onEvent: (CatalogEvent) -> Unit
    ) = ItemScreen(
        modifier = modifier,
        itemType = ICatalogItem.Companion.CatalogItemType.CLINIC,
        link = clinic.link,
        name = clinic.name,
        coords = clinic.coords,
        address = clinic.address,
        phoneNumber = clinic.phoneNumber,
        reviews = clinic.reviews,
        onEvent = onEvent
    )

    /**
     * Helper component that renders an information row with an icon and label.
     *
     * @param modifier Modifier for the layout.
     * @param title The label text to display.
     * @param subtitle Optional detail text under the title.
     * @param icon The icon vector graphic.
     * @param iconTint Vector icon tint color.
     * @param iconBackgroundTint Background color of the icon container.
     * @param titleColor Text color of the title.
     * @param subtitleColor Text color of the subtitle.
     */
    @Composable
    private fun InfoCard(
        modifier: Modifier = Modifier,
        title: String,
        subtitle: String? = null,
        icon: HIcons,
        iconTint: Color = HTheme.colors.onBackground,
        iconBackgroundTint: Color = HTheme.colors.onBackgroundContainer,
        titleColor: Color = HTheme.colors.onBackground,
        subtitleColor: Color = HTheme.colors.secondary
    ) = Row(
        modifier = modifier.height(IntrinsicSize.Max),
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
                    modifier = Modifier.size(30.dp),
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
                modifier = Modifier.wrapContentWidth(),
                text = title,
                color = titleColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )

            subtitle?.let {
                HText.Default(
                    modifier = Modifier.wrapContentWidth(),
                    text = subtitle,
                    color = subtitleColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }

    /**
     * A skeleton loading screen row that simulates an InfoCard.
     *
     * @param modifier Modifier for the layout.
     */
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

    /**
     * Row of buttons for primary actions at the bottom of the screen (Back, Route, Phone).
     *
     * @param modifier Modifier for the layout.
     * @param onBack Callback for when the back button is clicked.
     * @param onRoute Callback to open navigation maps for the address.
     * @param onPhone Callback to start a phone call.
     */
    @Composable
    private fun BottomButtons(
        modifier: Modifier = Modifier,
        onBack: (() -> Unit)? = null,
        onRoute: (() -> Unit)? = null,
        onPhone: (() -> Unit)? = null,
    ) = Row(
        modifier = modifier
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        onBack?.let {
            HContainer.Default(
                modifier = Modifier
                    .wrapContentWidth()
                    .aspectRatio(1f)
                    .clickable(
                        onClick = it,
                        interactionSource = null,
                        indication = null
                    ),
                paddingValues = PaddingValues.Zero
            ) {
                HIcons.HOME(
                    modifier = Modifier.fillMaxSize(0.55f),
                    tint = HTheme.colors.onBackground
                )
            }
        }

        onRoute?.let {
            HContainer.Default(
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        onClick = it,
                        interactionSource = null,
                        indication = null
                    ),
                backgroundColor = HTheme.colors.primary
            ) {
                HText.SingleLine(
                    text = stringResource(feo.health.ui.R.string.route).capitalize(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = HTheme.colors.background
                )
            }
        }

        onPhone?.let {
            if (onRoute != null)
                HContainer.Default(
                    modifier = Modifier
                        .wrapContentWidth()
                        .aspectRatio(1f)
                        .clickable(
                            onClick = it,
                            interactionSource = null,
                            indication = null
                        ),
                    paddingValues = PaddingValues.Zero
                ) {
                    HIcons.PHONE(
                        modifier = Modifier.fillMaxSize(0.55f),
                        tint = HTheme.colors.onBackground
                    )
                }
            else
                HContainer.Default(
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            onClick = it,
                            interactionSource = null,
                            indication = null
                        ),
                    backgroundColor = HTheme.colors.primary
                ) {
                    HText.SingleLine(
                        text = stringResource(feo.health.ui.R.string.phone).capitalize(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = HTheme.colors.background
                    )
                }
        }
    }

    /**
     * Renders the loading skeleton for organization pages.
     *
     * @param params Expected to contain a single [String] representing the screen title at index 0.
     */
    @Composable
    override fun LoadingScreen(vararg params: Any) = HContainer.TitledScreen(
        modifier = Modifier.fillMaxSize(),
        title = params[0] as String
    ) {

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            HContainer.Default(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.76f)
                    .padding(bottom = 10.dp)
                    .shimmer(defaultShimmer),
                backgroundColor = HTheme.colors.primary,
                paddingValues = PaddingValues.Zero
            )

            LazyColumn(
                userScrollEnabled = false,
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.Start
            ) {
                val infoCardsModifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()

                repeat(6) {
                    item {
                        ShimmerInfoCard(modifier = infoCardsModifier)
                    }
                }
            }
        }
    }
}