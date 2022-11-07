package de.niklasbednarczyk.openweathermap.data.onecall.values.number

@JvmInline
value class DistanceValue private constructor(override val value: Long) : NumberValue {

    override val roundingType: NumberRoundingType
        get() = NumberRoundingType.ZERO_DIGITS

    companion object {

        internal fun from(value: Long?): DistanceValue? {
            return if (value != null) {
                val roundedValue = value.div(1000) // m to km
                DistanceValue(roundedValue)
            } else null
        }

    }

}