package de.niklasbednarczyk.nbweather.feature.forecast.extensions

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.ui.colors.NBColors
import de.niklasbednarczyk.nbweather.core.ui.colors.NBColorsModel
import de.niklasbednarczyk.nbweather.data.onecall.values.units.DistanceUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PercentUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PrecipitationUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PressureUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.ProbabilityUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.UVIndexUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.WindSpeedUnitsValue

val NBUnitsValue.color
    @Composable
    get() = getColor(NBColors.colors) ?: LocalContentColor.current

fun NBUnitsValue.getColor(
    colors: NBColorsModel
): Color? = when(this) {
    is DistanceUnitsValue -> colors.unitsDistance
    is PercentUnitsValue -> colors.unitsPercent
    is PrecipitationUnitsValue -> colors.unitsPrecipitation
    is PressureUnitsValue -> colors.unitsPressure
    is ProbabilityUnitsValue -> colors.unitsProbability
    is TemperatureUnitsValue.Long, is TemperatureUnitsValue.Short -> colors.unitsTemperature
    is UVIndexUnitsValue -> colors.unitsUVIndex
    is WindSpeedUnitsValue -> colors.unitsWindSpeed
    else -> null

}