package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.models.CurrentWeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.types.moon.MoonPhaseType

data class ForecastOverviewSunAndMoonModel(
    private val currentTime: NBDateTimeDisplayModel,
    val sunrise: NBDateTimeDisplayModel,
    val sunset: NBDateTimeDisplayModel,
    val moonrise: NBDateTimeDisplayModel,
    val moonset: NBDateTimeDisplayModel,
    val moonPhase: MoonPhaseType
) : ForecastOverviewItem {

    val sunArcPercentage: Float
        get() {
            val current = currentTime.dt.value
            val min = sunrise.dt.value
            val max = sunset.dt.value

            val spanMinMax = max - min
            val spanCurrent = current - min
            return spanCurrent.toFloat() / spanMinMax
        }

    companion object {

        fun from(
            timezoneOffset: NBTimezoneOffsetValue?,
            currentWeather: CurrentWeatherModelData?,
            today: DailyForecastModelData?
        ): ForecastOverviewSunAndMoonModel? {
            return nbNullSafe(
                NBDateTimeDisplayModel.from(currentWeather?.currentTime, timezoneOffset),
                NBDateTimeDisplayModel.from(today?.sunrise, timezoneOffset),
                NBDateTimeDisplayModel.from(today?.sunset, timezoneOffset),
                NBDateTimeDisplayModel.from(today?.moonrise, timezoneOffset),
                NBDateTimeDisplayModel.from(today?.moonset, timezoneOffset),
                today?.moonPhase
            ) { currentTime, sunrise, sunset, moonrise, moonset, moonPhase ->
                if (sunrise.dt.value >= sunset.dt.value) return null

                ForecastOverviewSunAndMoonModel(
                    currentTime = currentTime,
                    sunrise = sunrise,
                    sunset = sunset,
                    moonrise = moonrise,
                    moonset = moonset,
                    moonPhase = moonPhase
                )
            }
        }

    }

}