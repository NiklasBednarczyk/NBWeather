package de.niklasbednarczyk.openweathermap.data.onecall.values.number

@JvmInline
value class UVIndexValue private constructor(override val value: Double) : NumberValue {

    override val roundingType: NumberRoundingType
        get() = NumberRoundingType.ZERO_DIGITS

    companion object {

        internal fun from(value: Double?): UVIndexValue? {
            return if (value != null) UVIndexValue(value) else null
        }

    }

}