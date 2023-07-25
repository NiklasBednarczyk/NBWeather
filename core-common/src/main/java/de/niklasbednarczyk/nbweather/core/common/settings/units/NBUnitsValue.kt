package de.niklasbednarczyk.nbweather.core.common.settings.units

import de.niklasbednarczyk.nbweather.core.common.R
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import java.text.DecimalFormat

interface NBUnitsValue {

    companion object {
        private val REGEX_NEGATIVE_ZERO = Regex("^-(?=0(\\.0*)?$)")
    }

    val value: Number

    fun getConvertedValue(units: NBUnitsModel): Double

    fun getSymbol(units: NBUnitsModel): NBString?

    fun getFractionDigits(units: NBUnitsModel): Int {
        return 0
    }

    fun shouldAddSpaceBetweenValueAndSymbol(units: NBUnitsModel): Boolean {
        return true
    }

    private fun getFormattedValue(units: NBUnitsModel): String {
        val convertedValue = getConvertedValue(units)
        val fractionDigits = getFractionDigits(units)

        val decimalFormat = DecimalFormat()
        decimalFormat.minimumFractionDigits = fractionDigits
        decimalFormat.maximumFractionDigits = fractionDigits

        val formattedValue = decimalFormat.format(convertedValue)
        return formattedValue.replace(REGEX_NEGATIVE_ZERO, "")
    }


    fun getRoundedValue(units: NBUnitsModel): Double? =
        getFormattedValue(units).toDoubleOrNull()

    fun getDisplayValue(units: NBUnitsModel): NBString? =
        NBString.Value.from(getFormattedValue(units))

    fun getDisplayValueWithSymbol(units: NBUnitsModel): NBString? {
        val resId = if (shouldAddSpaceBetweenValueAndSymbol(units)) {
            R.string.format_units_value_with_symbol_with_space
        } else {
            R.string.format_units_value_with_symbol_without_space
        }

        return NBString.Resource(
            resId,
            getDisplayValue(units),
            getSymbol(units)
        )
    }

}