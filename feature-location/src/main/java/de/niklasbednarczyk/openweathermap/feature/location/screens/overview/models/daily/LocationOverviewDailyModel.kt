package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.daily

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueItem
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.extensions.icon
import de.niklasbednarczyk.openweathermap.feature.location.extensions.probabilityOfPrecipitationValue
import kotlin.math.abs

data class LocationOverviewDailyModel(
    val forecastTimeValue: Long,
    val weekday: OwmString?,
    val dayOfMonth: OwmString?,
    val weatherIcon: OwmIconModel,
    val temperatures: LocationOverviewDailyTemperaturesModel,
    val probabilityOfPrecipitation: OwmValueItem,
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
                    owmNullSafe(dailyForecast.probabilityOfPrecipitation) { probabilityOfPrecipitation ->
                        probabilityOfPrecipitationValue(probabilityOfPrecipitation)
                    }

                owmNullSafe(
                    dailyForecast.forecastTime?.value,
                    weatherIcon,
                    temperatures,
                    probabilityOfPrecipitation,
                ) { forecastTimeValue, wIcon, temp, pop ->
                    LocationOverviewDailyModel(
                        forecastTimeValue = forecastTimeValue,
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