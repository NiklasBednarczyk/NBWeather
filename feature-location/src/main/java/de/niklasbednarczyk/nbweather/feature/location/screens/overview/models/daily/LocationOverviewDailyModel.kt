package de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.daily

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueItem
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.feature.location.extensions.icon
import de.niklasbednarczyk.nbweather.feature.location.extensions.probabilityOfPrecipitationValue
import kotlin.math.abs

data class LocationOverviewDailyModel(
    val forecastTime: Long,
    val weekday: NBString?,
    val dayOfMonth: NBString?,
    val weatherIcon: NBIconModel,
    val temperatures: LocationOverviewDailyTemperaturesModel,
    val probabilityOfPrecipitation: NBValueItem,
) {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): List<LocationOverviewDailyModel> {
            val timezoneOffset = oneCall.metadata.timezoneOffset

            val minTemperatureAll = oneCall.dailyForecasts.minByOrNull { daily ->
                daily.temperature?.minDailyTemperature?.roundedValue ?: Double.MAX_VALUE
            }?.temperature?.minDailyTemperature?.roundedValue ?: return emptyList()
            val maxTemperatureAll = oneCall.dailyForecasts.maxByOrNull { daily ->
                daily.temperature?.maxDailyTemperature?.roundedValue ?: Double.MIN_VALUE
            }?.temperature?.maxDailyTemperature?.roundedValue ?: return emptyList()
            val temperatureDifferenceAll = abs(maxTemperatureAll.minus(minTemperatureAll))

            return oneCall.dailyForecasts.mapNotNull { dailyForecast ->
                val weekday =
                    dailyForecast.forecastTime?.getDateWeekdayAbbreviationString(timezoneOffset)

                val dayOfMonth = dailyForecast.forecastTime?.getDateDayOfMonthString(timezoneOffset)

                val weatherIcon = dailyForecast.weather?.icon?.type?.icon

                val temperatures = LocationOverviewDailyTemperaturesModel.from(
                    dailyForecast = dailyForecast,
                    minTemperatureAll = minTemperatureAll,
                    maxTemperatureAll = maxTemperatureAll,
                    temperatureDifferenceAll = temperatureDifferenceAll
                )

                val probabilityOfPrecipitation =
                    nbNullSafe(dailyForecast.probabilityOfPrecipitation) { probabilityOfPrecipitation ->
                        probabilityOfPrecipitationValue(probabilityOfPrecipitation)
                    }

                nbNullSafe(
                    dailyForecast.forecastTime?.value,
                    weatherIcon,
                    temperatures,
                    probabilityOfPrecipitation,
                ) { forecastTime, wIcon, temp, pop ->
                    LocationOverviewDailyModel(
                        forecastTime = forecastTime,
                        weekday = weekday,
                        dayOfMonth = dayOfMonth,
                        weatherIcon = wIcon,
                        temperatures = temp,
                        probabilityOfPrecipitation = pop
                    )
                }
            }
        }

    }


}