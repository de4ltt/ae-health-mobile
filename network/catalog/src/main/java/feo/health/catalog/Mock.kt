package feo.health.catalog

import feo.health.catalog.clinic.dto.ClinicDto
import feo.health.catalog.doctor.dto.DoctorDto
import feo.health.catalog.doctor.dto.DoctorSpecialityDto
import feo.health.catalog.pharmacy.dto.PharmacyDto
import feo.health.catalog.search.dto.ReviewDto
import feo.health.catalog.services.dto.ServiceDto
import java.time.LocalDate

object Mock {
    val specialities = listOf(
        DoctorSpecialityDto(
            name = "Отоларинголог",
            link = "link/0"
        ),
        DoctorSpecialityDto(
            name = "Хирург",
            link = "link/1"
        ),
        DoctorSpecialityDto(
            name = "Мочекаменщик",
            link = "link/2"
        ),
        DoctorSpecialityDto(
            name = "It's a Parasite Eve",
            link = "link/3"
        ),
        DoctorSpecialityDto(
            name = "Ненавистник Америки",
            link = "link/4"
        ),
    )
    val reviews = listOf(
        ReviewDto(
            text = "",
            date = LocalDate.of(2023, 12, 1),
            rating = 1.21
        ),
        ReviewDto(
            text = "Боже, как же мне хорошо",
            date = LocalDate.of(2023, 12, 1),
            rating = 1.toDouble()
        ),
        ReviewDto(
            text = "Мне не очень хорошо. Хочется кричать АААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААААА",
            date = LocalDate.of(2023, 12, 1),
            rating = 2.toDouble()
        ),
        ReviewDto(
            text = "Мяу, у меня деменция. Мяу, у меня деменция. Мяу, у меня деменция. Мяу, у меня деменция. Мяу, у меня деменция. Мяу, у меня деменция. Мяу, у меня деменция.",
            date = LocalDate.of(2023, 12, 1),
            rating = 3.toDouble()
        ),
        ReviewDto(
            text = "РРРРРРРРРРРРРРРРРРРРРРРРРРРРРРРРоссия священная наша держава... Дальше не помню, обращайтесь к пациенту с деменцией",
            date = LocalDate.of(2023, 12, 1),
            rating = 4.toDouble()
        ),
        ReviewDto(
            text = "хаха ХХАХхах хах ХАХхахАХ Хха хХА ХхахАХхахХАХАХАХХАХАХАХХА. достаточно.",
            date = LocalDate.of(2023, 12, 1),
            rating = 5.toDouble()
        ),
    )
    val doctors = List(5) { index ->
        DoctorDto(
            name = "Докторов Доктор Докторович",
            link = "link",
            specialities = specialities,
            experience = index,
            imageUri = "https://yastatic.net/naydex/yandex-search/dnu6fe516/1ba0e0YXJkDm/Ln1TmJxAWzhxG5ThWEf68zn-qr9kq255UO28lMqBGvV879_PhCf4w1iSjT-Gybww8rYis2Z0YwlMd-QXYbbAxQ8NUnn7Var6tggW45v1WFVLbGkaBMvffdE5vPUIM3",
            rating = (index % 6).toDouble(),
            itemType = "doctor",
            reviews = reviews
        )
    }
    val clinics = List(5) {
        ClinicDto(
            name = "Республиканская больница имени Яндекса и МАXа",
            link = "clinic-1431",
            address = "г. Путиняево, ул. Верховного главнокомандующего, д. 1488",
            phoneNumber = "+79996667733",
            imageUri = "https://yandex-images.clstorage.net/51DFco214/4a6cdb784pk/0culCFUZfXTQ7ayOLBf66Ms7Z1kta3PfYhk-8CQNU4TMArmXeptHIsFByDg2vCR9pJEJd79IHmcyXKb0LSXPNsCIJY_DCGlnhGn9Se_CKCiOv6aZ4-KoFKvT5v1vAYqMTkIpwi6JwLPQ2tQpd-ttIL0S3qGn1omqr0VroTR9TGtUWLDe0Vp9sV3pot8kGjf6z71sWKmnJtDpXSO5JQ3AhsVLps4tDxi8WZSasL1hqm93F5BtqJ0KKqvMa6dwfYzr0Nu-G97fPPvVIekfahr69wS4qlsv7SsV6BhnOuABjc2OACrPqoeT_R-e2TmyZOGlOdwZqakfAKVoxOckNPOPLNGfdNNYmn8xFeAg1WXfP74AvimY7Sdokq6R6zfmw0ACiglqiGtBX75eHda7fOznI_2TXK4pnsZvpIBlKLD-wqvcH_JbXZLzst3iIdnuWrS9BfLsFqpvJlUr0Ksz74lDwYqBa0IpTB06GNBQMPoh5eM4mNrn6N-AaumNa6AydYNoUFY-Gt6d8TlQZGQe45_3sks6KJJtbyTdadRpMStNQobIR2HFpw8Qv5jc3vv5bayj8NfeL6HdhymljKApNbYOY9SUOtafGDd0keJrFK0VsD3Muuxaoe9pXmoVKbspTcWNTM7jgK8DUveW19F392upIzdfny-jncmkao0m4nd2xWqTVLQfWR0x_9bqLN8qULb5wX_tGebs4RwpWOk5YYPLggIKqgnqBZqy1lyS8vfu7yvwFFGgY1jAJqME7ib9uIkhHBR301PXtD-eqyNeKtW-8Ij1KNylKCgZbt-gfKPIjcbLzyELrsZYNlSZ37nyI6bruxHQ6mrYAKRjzCCrfLYPLZVVf1ZX2XA6lCIkliKccD7GcGEapqviHmrcZP3vCs1GRMlui2ONUPKWU1e_e-Vkaf1TFi_q0khp4gwja7K6y6HcE7ZWGxGxf9FipRjs3Xe6yT7u06dl4h3p2mk9ps",
            reviews = reviews,
            itemType = "clinic"
        )
    }
    val pharmacies = List(5) {
        PharmacyDto(
            name = "Великая аптека Яндекса",
            phoneNumber = "+79788819289",
            website = "www.yandex.cum",
            address = "ул. им. ВК Видео, д. 1488",
            openingHours = listOf(
                "Пн-Пт 00:00 - 25:00", "Сб 10:00 - 12:00", "Вс 00:00 - 13:00"
            )
        )
    }
    val services = List(5) {
        ServiceDto(
            name = "Приколюха $it",
            link = "link/$it",
        )
    }
}