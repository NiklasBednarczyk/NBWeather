package de.niklasbednarczyk.nbweather.data.onecall.values.forecast

import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue

sealed interface ForecastValue {

    sealed interface Units : ForecastValue {
        val unitsValue: NBUnitsValue
    }

}