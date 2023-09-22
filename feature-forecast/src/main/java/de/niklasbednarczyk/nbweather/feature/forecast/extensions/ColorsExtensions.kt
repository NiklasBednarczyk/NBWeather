package de.niklasbednarczyk.nbweather.feature.forecast.extensions

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.ui.colors.NBColors
import de.niklasbednarczyk.nbweather.data.onecall.values.units.DistanceValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PercentValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PrecipitationValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PressureValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.ProbabilityValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.UVIndexValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.WindSpeedValue

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