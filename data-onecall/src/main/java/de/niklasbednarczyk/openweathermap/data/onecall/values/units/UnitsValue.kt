package de.niklasbednarczyk.openweathermap.data.onecall.values.units

import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import java.text.DecimalFormat

sealed interface UnitsValue {

    companion object {
        private val REGEX_NEGATIVE_ZERO = Regex("^-(?=0(\\.0*)?$)")
    }

    val value: Number

    val roundingType: RoundingType

    val formattedValue: String
        get() {
            val pattern = when (roundingType) {
                RoundingType.ZERO_DIGITS -> "#,##0"
                RoundingType.ONE_DIGIT -> "#,##0.0"
            }
            val decimalFormat = DecimalFormat(pattern)
            val formattedValue = decimalFormat.format(value)
            return formattedValue.replace(REGEX_NEGATIVE_ZERO, "")
        }

    val roundedValue: Double?
        get() = formattedValue.toDoubleOrNull()

    val displayValue: OwmString?
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