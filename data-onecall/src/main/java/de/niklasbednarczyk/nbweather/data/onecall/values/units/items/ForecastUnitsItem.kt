package de.niklasbednarczyk.nbweather.data.onecall.values.units.items

import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue

sealed interface ForecastUnitsItem {

    val unitsValue: NBUnitsValue

}