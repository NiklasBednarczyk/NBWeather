package de.niklasbednarczyk.openweathermap.data.onecall.values.number

@JvmInline
value class PrecipitationValue private constructor(override val value: Double) : NumberValue {

    override val roundingType: NumberRoundingType
        get() = NumberRoundingType.ONE_DIGIT

    companion object {

        internal fun from(value: Double?): PrecipitationValue? {
            return if (value != null) PrecipitationValue(value) else null
        }

    }

}