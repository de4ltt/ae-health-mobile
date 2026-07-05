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

/**
 * Serializer for [LocalDateTime] using standard formats (e.g. YYYY-MM-DD'T'HH:mm:ss.SSSSSS).
 */
object LocalDateTimeSerializer : KSerializer<LocalDateTime> {

    /**
     * Formatter specification matching local date and time fraction.
     */
    private val formatter: DateTimeFormatter = DateTimeFormatterBuilder()
        .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
        .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
        .toFormatter()

    /**
     * Serializer descriptor specifying Primitive STRING encoding structure.
     */
    override val descriptor =
        PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

    /**
     * Encodes a [LocalDateTime] instance to string format.
     *
     * @param encoder Serial output encoder context.
     * @param value The value to serialize.
     */
    override fun serialize(encoder: Encoder, value: LocalDateTime) =
        encoder.encodeString(value.format(formatter))

    /**
     * Decodes a string representation to [LocalDateTime] instance.
     *
     * @param decoder Serial input decoder context.
     * @return Decoded [LocalDateTime] instance.
     */
    override fun deserialize(decoder: Decoder): LocalDateTime =
        LocalDateTime.parse(decoder.decodeString(), formatter)
}

