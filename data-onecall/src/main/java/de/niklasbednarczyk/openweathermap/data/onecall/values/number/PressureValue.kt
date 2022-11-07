package de.niklasbednarczyk.openweathermap.data.onecall.values.number

@JvmInline
value class PressureValue private constructor(override val value: Long) : NumberValue {

    override val roundingType: NumberRoundingType
        get() = NumberRoundingType.ZERO_DIGITS

    companion object {

        internal fun from(value: Long?): PressureValue? {
            return if (value != null) PressureValue(value) else null
        }

    }

}