package de.niklasbednarczyk.nbweather.core.common.settings.units

import de.niklasbednarczyk.nbweather.core.common.R
import de.niklasbednarczyk.nbweather.core.common.string.NBString

enum class NBDistanceUnitType : NBUnitType {
    KILOMETER,
    MILE;

    override val symbol: NBString?
        get() {
            val resId = when (this) {
                KILOMETER -> R.string.unit_symbol_distance_kilometer
                MILE -> R.string.unit_symbol_distance_mile
            }
            return NBString.Resource(resId)
        }

}