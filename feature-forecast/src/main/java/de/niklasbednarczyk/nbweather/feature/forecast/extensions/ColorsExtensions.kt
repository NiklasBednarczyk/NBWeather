package de.niklasbednarczyk.nbweather.feature.forecast.extensions

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.ui.colors.NBColors
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
    get() = when (this) {
        is DistanceUnitsValue -> NBColors.colors.unitsDistance
        is PercentUnitsValue -> NBColors.colors.unitsPercent
        is PrecipitationUnitsValue -> NBColors.colors.unitsPrecipitation
        is PressureUnitsValue -> NBColors.colors.unitsPressure
        is ProbabilityUnitsValue -> NBColors.colors.unitsProbability
        is TemperatureUnitsValue.Long, is TemperatureUnitsValue.Short -> NBColors.colors.unitsTemperature
        is UVIndexUnitsValue -> NBColors.colors.unitsUVIndex
        is WindSpeedUnitsValue -> NBColors.colors.unitsWindSpeed
        else -> LocalContentColor.current
    }