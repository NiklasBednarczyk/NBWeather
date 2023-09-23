package de.niklasbednarczyk.nbweather.feature.forecast.extensions

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.ui.colors.NBColors
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.DistanceValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.PercentValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.PrecipitationValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.PressureValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.ProbabilityValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.TemperatureValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.UVIndexValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.WindSpeedValue

val NBUnitsValue.color
    @Composable
    get() = when (this) {
        is DistanceValue -> NBColors.colors.unitsDistance
        is PercentValue -> NBColors.colors.unitsPercent
        is PrecipitationValue -> NBColors.colors.unitsPrecipitation
        is PressureValue -> NBColors.colors.unitsPressure
        is ProbabilityValue -> NBColors.colors.unitsProbability
        is TemperatureValue.Long, is TemperatureValue.Short -> NBColors.colors.unitsTemperature
        is UVIndexValue -> NBColors.colors.unitsUVIndex
        is WindSpeedValue -> NBColors.colors.unitsWindSpeed
        else -> LocalContentColor.current
    }