package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.header

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.data.onecall.models.MinutelyForecastModelData
import de.niklasbednarczyk.openweathermap.data.onecall.values.datetime.TimezoneOffsetValue
import kotlin.math.max

data class LocationOverviewTodayPrecipitationModel(
    private val forecasts: List<Double>,
    val currentTime: OwmString?
) {

    val factors: List<Float>
        get() = forecasts.map { forecast ->
            val value = 1 - forecast.div(FORECAST_HIGHEST_VALUE).toFloat()
            max(value, 0f)
        }


    val headline: OwmString?
        get() {
            val firstElement = forecasts.firstOrNull()
                ?: return OwmString.Resource(R.string.screen_location_overview_today_precipitation_text_no_data)

            return when {
                forecasts.all { forecast -> forecast == 0.0 } -> {
                    OwmString.Resource(R.string.screen_location_overview_today_precipitation_text_none)
                }
                forecasts.all { forecast -> forecast > 0.0 } -> {
                    OwmString.Resource(R.string.screen_location_overview_today_precipitation_text_full)
                }
                firstElement == 0.0 -> {
                    val changeIndex = forecasts.indexOfFirst { forecast -> forecast > 0.0 }
                    OwmString.Resource(
                        R.string.screen_location_overview_today_precipitation_text_starting,
                        changeIndex
                    )
                }
                firstElement > 0.0 -> {
                    val changeIndex = forecasts.indexOfFirst { forecast -> forecast == 0.0 }

                    val isBreaking = forecasts
                        .slice(0..changeIndex)
                        .any { forecast ->
                            forecast > 0.0
                        }

                    if (isBreaking) {
                        OwmString.Resource(
                            R.string.screen_location_overview_today_precipitation_text_breaking,
                            changeIndex
                        )
                    } else {
                        OwmString.Resource(
                            R.string.screen_location_overview_today_precipitation_text_ending,
                            changeIndex
                        )
                    }
                }
                else -> null
            }
        }


    companion object {

        private const val FORECAST_SIZE_LIMIT = 60
        private const val FORECAST_HIGHEST_VALUE = 50

        fun from(
            minutelyForecasts: List<MinutelyForecastModelData>,
            timeFormat: OwmTimeFormatType,
            timezoneOffset: TimezoneOffsetValue?
        ): LocationOverviewTodayPrecipitationModel {

            val forecasts = minutelyForecasts.mapNotNull { minutelyForecast ->
                minutelyForecast.precipitation?.value
            }.take(FORECAST_SIZE_LIMIT)

            val currentForecastTime = minutelyForecasts.firstOrNull()?.forecastTime
            val currentTime = currentForecastTime?.getTimeString(timezoneOffset, timeFormat)


            return LocationOverviewTodayPrecipitationModel(
                forecasts = forecasts,
                currentTime = currentTime
            )
        }

    }


}