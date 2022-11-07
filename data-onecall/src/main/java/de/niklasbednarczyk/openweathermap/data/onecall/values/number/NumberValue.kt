package de.niklasbednarczyk.openweathermap.data.onecall.values.number

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import java.text.DecimalFormat

interface NumberValue {

    val value: Number

    val roundingType: NumberRoundingType

    val displayText: OwmString
        get() {
            val pattern = when (roundingType) {
                NumberRoundingType.ZERO_DIGITS -> "#,##0"
                NumberRoundingType.ONE_DIGIT -> "#,##0.0"
            }
            val decimalFormat = DecimalFormat(pattern)
            val formattedValue = decimalFormat.format(value)
            return OwmString.Value.from(formattedValue)
        }

}