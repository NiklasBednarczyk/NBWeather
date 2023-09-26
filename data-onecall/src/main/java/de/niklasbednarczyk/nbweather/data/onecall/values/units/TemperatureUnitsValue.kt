package de.niklasbednarczyk.nbweather.data.onecall.values.units

import de.niklasbednarczyk.nbweather.core.common.settings.units.NBTemperatureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.R

@JvmInline
value class TemperatureUnitsValue internal constructor(internal val value: Double) {

    @JvmInline
    value class Long(override val value: Double) : NBUnitsValue {

        override fun getConvertedValue(units: NBUnitsModel): Double {
            return getConvertedValuePrivate(value, units)
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

        fun toShort() = Short(value)

        companion object {

            fun Long?.orZero() = this ?: Long(0.0)

        }

    }

    @JvmInline
    value class Short(override val value: Double) : NBUnitsValue {

        override fun getConvertedValue(units: NBUnitsModel): Double {
            return getConvertedValuePrivate(value, units)
        }

        override fun getSymbol(units: NBUnitsModel): NBString? {
            return when (units.temperatureUnit) {
                NBTemperatureUnitType.CELSIUS, NBTemperatureUnitType.FAHRENHEIT -> NBString.ResString(
                    R.string.unit_symbol_temperature_short_degree
                )

                NBTemperatureUnitType.KELVIN -> null
            }
        }

        override fun shouldAddSpaceBetweenValueAndSymbol(units: NBUnitsModel): Boolean {
            return false
        }

        companion object {

            fun Short?.orZero() = this ?: Short(0.0)

        }
    }

    fun getLong() = Long(value)

    fun getShort() = Short(value)

    companion object {

        private fun getConvertedValuePrivate(value: Double, units: NBUnitsModel): Double {
            return when (units.temperatureUnit) {
                NBTemperatureUnitType.CELSIUS -> value - 273.15 // kelvin to celsius
                NBTemperatureUnitType.FAHRENHEIT -> (value - 273.15) * 9 / 5 + 32 // kelvin to fahrenheit
                NBTemperatureUnitType.KELVIN -> value // kelvin to kelvin
            }
        }

    }

}