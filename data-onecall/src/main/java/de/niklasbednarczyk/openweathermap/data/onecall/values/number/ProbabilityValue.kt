package de.niklasbednarczyk.openweathermap.data.onecall.values.number

@JvmInline
value class ProbabilityValue private constructor(override val value: Double) : NumberValue {

    override val roundingType: NumberRoundingType
        get() = NumberRoundingType.ZERO_DIGITS

    companion object {

        internal fun from(value: Double?): ProbabilityValue? {
            return if (value != null) ProbabilityValue(value) else null
        }

    }

}