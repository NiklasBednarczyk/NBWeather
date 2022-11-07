package de.niklasbednarczyk.openweathermap.data.onecall.values.number

@JvmInline
value class PercentValue private constructor(override val value: Long) : NumberValue {

    override val roundingType: NumberRoundingType
        get() = NumberRoundingType.ZERO_DIGITS

    companion object {

        internal fun from(value: Long?): PercentValue? {
            return if (value != null) PercentValue(value) else null
        }

    }

}