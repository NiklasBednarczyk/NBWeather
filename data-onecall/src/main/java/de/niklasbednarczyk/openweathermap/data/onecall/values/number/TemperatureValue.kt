package de.niklasbednarczyk.openweathermap.data.onecall.values.number

@JvmInline
value class TemperatureValue private constructor(override val value: Double) : NumberValue {

    override val roundingType: NumberRoundingType
        get() = NumberRoundingType.ZERO_DIGITS

    companion object {

        internal fun from(value: Double?): TemperatureValue? {
            return if (value != null) TemperatureValue(value) else null
        }

    }

}