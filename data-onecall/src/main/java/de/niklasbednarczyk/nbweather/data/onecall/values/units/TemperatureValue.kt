package de.niklasbednarczyk.nbweather.data.onecall.values.units

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBTemperatureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.R

@JvmInline
value class TemperatureValue private constructor(internal val valueInternal: Double) {

    private fun getConvertedValuePrivate(units: NBUnitsModel): Double {
        return when (units.temperatureUnit) {
            NBTemperatureUnitType.CELSIUS -> valueInternal.minus(273.15) // kelvin to celsius
            NBTemperatureUnitType.FAHRENHEIT -> (valueInternal.minus(273.15))
                .times(9)
                .div(5)
                .plus(32) // kelvin to fahrenheit
            NBTemperatureUnitType.KELVIN -> valueInternal // kelvin to kelvin
        }
    }

    fun getLong() = object : NBUnitsValue {
        override val value: Number
            get() = valueInternal

        override fun getConvertedValue(units: NBUnitsModel): Double {
            return getConvertedValuePrivate(units)
        }

        override fun getSymbol(units: NBUnitsModel): NBString? {
            return units.temperatureUnit.symbol
        }

        override fun shouldAddSpaceBetweenValueAndSymbol(units: NBUnitsModel): Boolean {
            return when (units.temperatureUnit) {
                NBTemperatureUnitType.CELSIUS, NBTemperatureUnitType.FAHRENHEIT -> true
                NBTemperatureUnitType.KELVIN -> false
            }
        }
    }

    fun getShort() = object : NBUnitsValue {
        override val value: Number
            get() = valueInternal

        override fun getConvertedValue(units: NBUnitsModel): Double {
            return getConvertedValuePrivate(units)
        }

        override fun getSymbol(units: NBUnitsModel): NBString? {
            return when (units.temperatureUnit) {
                NBTemperatureUnitType.CELSIUS, NBTemperatureUnitType.FAHRENHEIT -> NBString.Resource(
                    R.string.unit_symbol_temperature_short_degree
                )

                NBTemperatureUnitType.KELVIN -> null
            }
        }

        override fun shouldAddSpaceBetweenValueAndSymbol(units: NBUnitsModel): Boolean {
            return false
        }
    }


    companion object {

        fun from(value: Double?): TemperatureValue? {
            return nbNullSafe(value) { TemperatureValue(it) }
        }

    }

}