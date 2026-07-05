package feo.health.network.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Serializer for optional [LocalDate] using ISO format (e.g. YYYY-MM-DD).
 */
object LocalDateSerializer : KSerializer<LocalDate?> {
    /**
     * Formatter specification matching standard ISO local date.
     */
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    /**
     * Serializer descriptor specifying Primitive STRING encoding structure.
     */
    override val descriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    /**
     * Encodes a nullable [LocalDate] instance into string form.
     *
     * @param encoder Serial output encoder context.
     * @param value The value to serialize.
     */
    override fun serialize(encoder: Encoder, value: LocalDate?) =
        encoder.encodeString(value?.format(formatter) ?: "")

    /**
     * Decodes a string representing ISO date to nullable [LocalDate] instance.
     *
     * @param decoder Serial input decoder context.
     * @return Decoded [LocalDate] instance, or `null`.
     */
    override fun deserialize(decoder: Decoder): LocalDate? =
        LocalDate.parse(decoder.decodeString(), formatter)
}

