package feo.health.catalog.presentation.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import feo.health.catalog.domain.model.ReviewDomain
import feo.health.catalog.presentation.model.Clinic
import feo.health.catalog.presentation.model.Pharmacy
import feo.health.catalog.presentation.model.util.CatalogItemType
import feo.health.ui.component.HStrings.capitalize
import feo.health.ui.component.HText
import feo.health.ui.theme.HTheme

object LocationItemCard {

    @Composable
    private fun LocationItemCard(
        modifier: Modifier = Modifier,
        phoneNumber: String? = null,
        name: String? = null,
        website: String? = null,
        address: String? = null,
        openingHours: List<String>? = null,
        imageUri: String? = null,
        reviews: List<ReviewDomain>? = null,
        itemType: CatalogItemType,
        link: String
    ) = Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.Start
    ) {
        val titleColor = HTheme.colors.onBackground

        HText.SingleLine(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            text = name ?: itemType.name.capitalize(),
            color = { titleColor },
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        address?.let {

        }

        Spacer(modifier = Modifier.height(5.dp))


    }

    @Composable
    private fun InfoCard(
        modifier: Modifier = Modifier,
        title: String,
        subtitle: String,
        @DrawableRes icon: Int,
        iconTint: Color = HTheme.colors.onBackground,
        iconBackgroundTint: Color = HTheme.colors.onBackgroundContainer,
        titleColor: Color = HTheme.colors.onBackground,
        subtitleColor: Color = HTheme.colors.secondary
    ) = Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

    }

    @Composable
    fun PharmacyItemCard(
        modifier: Modifier = Modifier,
        pharmacy: Pharmacy,
        link: String
    ) = LocationItemCard(
        modifier = modifier,
        name = pharmacy.name,
        phoneNumber = pharmacy.phoneNumber,
        website = pharmacy.website,
        address = pharmacy.address,
        openingHours = pharmacy.openingHours,
        itemType = CatalogItemType.PHARMACY,
        link = link
    )

    @Composable
    fun ClinicItemCard(
        modifier: Modifier = Modifier,
        clinic: Clinic
    ) = LocationItemCard(
        modifier = modifier,
        itemType = CatalogItemType.CLINIC,
        link = clinic.link
    )
}