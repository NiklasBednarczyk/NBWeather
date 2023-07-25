package de.niklasbednarczyk.nbweather.core.common.settings.units

import de.niklasbednarczyk.nbweather.core.common.R
import de.niklasbednarczyk.nbweather.core.common.string.NBString

enum class NBPressureUnitType : NBUnitType {
    HECTOPASCAL,
    INCH_HG,
    MILLIMETER_OF_MERCURY;

    override val symbol: NBString?
        get() {
            val resId = when (this) {
                HECTOPASCAL -> R.string.unit_symbol_pressure_hectopascal
                INCH_HG -> R.string.unit_symbol_pressure_inch_hg
                MILLIMETER_OF_MERCURY -> R.string.unit_symbol_pressure_millimeter_of_mercury
            }
            return NBString.Resource(resId)
        }

}