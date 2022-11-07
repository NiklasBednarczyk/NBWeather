package de.niklasbednarczyk.openweathermap.data.onecall.values.number

@JvmInline
value class WindSpeedValue private constructor(override val value: Double) : NumberValue {

    override val roundingType: NumberRoundingType
        get() = NumberRoundingType.ZERO_DIGITS

    companion object {

        internal fun from(value: Double?): WindSpeedValue? {
            return if (value != null) WindSpeedValue(value) else null
        }

    }

}