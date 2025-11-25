package feo.health.network.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

object LocalDateTimeSerializer : KSerializer<LocalDateTime> {

    private val formatter: DateTimeFormatter = DateTimeFormatterBuilder()
        .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
        .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
        .toFormatter()

    override val descriptor =
        PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDateTime) =
        encoder.encodeString(value.format(formatter))

    override fun deserialize(decoder: Decoder): LocalDateTime =
        LocalDateTime.parse(decoder.decodeString(), formatter)
}

