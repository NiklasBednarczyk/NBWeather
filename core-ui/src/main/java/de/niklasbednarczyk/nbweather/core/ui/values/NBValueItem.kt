package de.niklasbednarczyk.nbweather.core.ui.values

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.common.string.NBString

sealed interface NBValueItem {

    data class Icon(
        val valueIcon: NBValueIconModel
    ) : NBValueItem

    data class IconWithUnits(
        val valueIcon: NBValueIconModel,
        val unitsValue: NBUnitsValue
    ) : NBValueItem

    data class Text(
        val text: NBString?,
    ) : NBValueItem

    data class Time(
        val dateTime: NBDateTimeDisplayModel
    ) : NBValueItem

    data class Units(
        val unitsValue: NBUnitsValue
    ) : NBValueItem

}