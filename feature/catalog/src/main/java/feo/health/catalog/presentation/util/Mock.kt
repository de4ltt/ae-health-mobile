package feo.health.catalog.presentation.util

import feo.health.catalog.presentation.model.DoctorSpeciality
import feo.health.catalog.presentation.model.ICatalog
import feo.health.catalog.presentation.model.Review
import java.time.LocalDate
import kotlin.random.Random

object Mock {
    val clinic = ICatalog.Clinic(
        name = "Республиканская больница имени Яндекса и МАXа",
        link = "clinic-1431",
        address = "г. Путиняево, ул. Верховного главнокомандующего, д. 1488",
        phoneNumber = "+79996667733",
        imageUri = "https://static.maps.2gis.com/1.0?s=880x380&c=55.75584,37.61782&z=14",
        reviews = listOf(
            Review(
                text = "Текст комментария",
                date = LocalDate.now(),
                rating = 3.5
            )
        ),
        itemType = "clinic"
    )

    val pharmacy = ICatalog.Pharmacy(
        name = "Великая аптека Яндекса",
        phoneNumber = "+79788819289",
        website = "www.yandex.cum",
        address = "ул. им. ВК Видео, д. 1488",
        openingHours = listOf(
            "Пн-Пт 00:00 - 25:00", "Сб 10:00 - 12:00", "Вс 00:00 - 13:00"
        )
    )

    private fun randItem() = listOf(clinic, pharmacy)[Random.nextInt(2)]
    val specialists = MutableList(40) { index ->
        ICatalog.Doctor(
            name = "Докторов Доктор Докторович",
            link = "link",
            specialities = List(7) {
                DoctorSpeciality(
                    name = "Агрессия #$it",
                    link = "link/$it"
                )
            },
            experience = index,
            imageUri = "https://nationaldoctorsday.org/wp-content/uploads/2025/02/2025-national-doctors-day-about.jpg",
            rating = (index % 6).toDouble(),
            itemType = "doctor",
            reviews = List(7) {
                Review(
                    text = "My text $it about doctor $index",
                    date = LocalDate.of(2023, it % 12 + 1, it % 28 + 1),
                    rating = (it % 6).toDouble()
                )
            }
        )
    }
}