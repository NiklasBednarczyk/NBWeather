package de.niklasbednarczyk.nbweather.core.common.settings.units

import de.niklasbednarczyk.nbweather.core.common.R
import de.niklasbednarczyk.nbweather.core.common.string.NBString

enum class NBPrecipitationUnitType : NBUnitType {
    INCH,
    MILLIMETER;

    override val symbol: NBString?
        get() {
            val resId = when (this) {
                INCH -> R.string.unit_symbol_precipitation_inch
                MILLIMETER -> R.string.unit_symbol_precipitation_millimeter
            }
            return NBString.Resource(resId)
        }

}