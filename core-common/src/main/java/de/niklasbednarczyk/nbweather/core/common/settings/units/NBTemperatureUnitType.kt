package de.niklasbednarczyk.nbweather.core.common.settings.units

import de.niklasbednarczyk.nbweather.core.common.R
import de.niklasbednarczyk.nbweather.core.common.string.NBString

enum class NBTemperatureUnitType : NBUnitType {
    CELSIUS,
    FAHRENHEIT,
    KELVIN;

    override val symbol: NBString?
        get() {
            val resId = when (this) {
                CELSIUS -> R.string.unit_symbol_temperature_celsius
                FAHRENHEIT -> R.string.unit_symbol_temperature_fahrenheit
                KELVIN -> R.string.unit_symbol_temperature_kelvin
            }
            return NBString.ResString(resId)
        }

}