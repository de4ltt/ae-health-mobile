package feo.health.catalog.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import feo.health.catalog.presentation.common.CatalogItem
import feo.health.catalog.presentation.common.SearchBar
import feo.health.catalog.presentation.model.Clinic
import feo.health.catalog.presentation.model.Review
import feo.health.catalog.presentation.model.util.CatalogItemType
import feo.health.catalog.presentation.model.util.Doctor
import feo.health.catalog.presentation.ui.LocationItemCard
import feo.health.catalog.presentation.viewmodel.CatalogViewModel
import feo.health.ui.component.HList
import java.time.LocalDate

@Composable
fun CatalogScreen(
    navHostController: NavHostController,
    catalogViewModel: CatalogViewModel
) {

    val clinic = Clinic(
        name = "Имя клиники",
        link = "clinic-1431",
        address = "г. Краснодар, ул. Горбачёва, д. 54",
        phoneNumber = "+797888192890",
        imageUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXQicZ3Hqf1JPXm9hR3IOv74H590fsZUBsHQ&s",
        reviews = listOf(
            Review(
                text = "Текст комментария",
                date = LocalDate.now(),
                rating = 5.0
            )
        )
    )

    LocationItemCard.ClinicItemCard(
        clinic = clinic
    )

/*
    Column(
        modifier = Modifier.fillMaxSize().animateContentSize()
    ) {

        SearchBar(
            modifier = Modifier,
            state = catalogViewModel.searchBarState,
            onSearch = {
                catalogViewModel.onSearch()
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        val doctors = List(40) {
            Doctor(
                title = "Докторов Доктор Докторович",
                link = "link",
                type = CatalogItemType.DOCTOR,
                imageUri = null,
            )
        }

        HList.LazyTitled(
            modifier = Modifier,
            listOrientation = HList.ListOrientation.VERTICAL,
            contentPadding = PaddingValues(0.dp),
            spacing = 10.dp,
            title = "Вы недавно смотрели",
            items = doctors,
            itemContainer = { CatalogItem(it) }
        )
    }
*/
}