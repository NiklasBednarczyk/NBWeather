package de.niklasbednarczyk.openweathermap.data.onecall.values.units

import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import java.text.DecimalFormat

sealed interface UnitsValue {

    val value: Number

    val roundingType: RoundingType

    val formattedValue: String
        get() {
            val pattern = when (roundingType) {
                RoundingType.ZERO_DIGITS -> "#,##0"
                RoundingType.ONE_DIGIT -> "#,##0.0"
            }
            val decimalFormat = DecimalFormat(pattern)
            return decimalFormat.format(value)
        }

    val displayValue: OwmString
        get() = OwmString.Value.from(formattedValue)

    interface Dependent : UnitsValue {
        fun getUnit(units: OwmUnitsType): OwmString
    }

    interface Independent : UnitsValue {
        val unit: OwmString
    }

    enum class RoundingType {

        ZERO_DIGITS,
        ONE_DIGIT

    }

}