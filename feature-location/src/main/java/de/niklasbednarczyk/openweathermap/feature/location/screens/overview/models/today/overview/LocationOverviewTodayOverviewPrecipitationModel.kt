package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.overview

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.data.onecall.models.MinutelyForecastModelData
import de.niklasbednarczyk.openweathermap.data.onecall.values.datetime.TimezoneOffsetValue
import kotlin.math.max

data class LocationOverviewTodayOverviewPrecipitationModel(
    private val forecasts: List<Double>,
    val currentTime: OwmString?
) {

    val factors: List<Float>
        get() = forecasts.map { forecast ->
            val multiplier = 1.0.div(FORECAST_STEP_COUNT)

            val factor = when (forecast) {
                in FORECAST_STEP_0_VALUE..FORECAST_STEP_1_VALUE -> forecast.div(FORECAST_STEP_1_VALUE).times(multiplier).plus(multiplier.times(0))
                in FORECAST_STEP_1_VALUE..FORECAST_STEP_2_VALUE -> forecast.div(FORECAST_STEP_2_VALUE).times(multiplier).plus(multiplier.times(1))
                in FORECAST_STEP_2_VALUE..FORECAST_STEP_3_VALUE -> forecast.div(FORECAST_STEP_3_VALUE).times(multiplier).plus(multiplier.times(2))
                in FORECAST_STEP_3_VALUE..FORECAST_STEP_4_VALUE -> forecast.div(FORECAST_STEP_4_VALUE).times(multiplier).plus(multiplier.times(3))
                else -> 1.0
            }

            val value = 1 - factor.toFloat()
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
        private const val FORECAST_STEP_0_VALUE = 0.0
        private const val FORECAST_STEP_1_VALUE = 1.0
        private const val FORECAST_STEP_2_VALUE = 10.0
        private const val FORECAST_STEP_3_VALUE = 25.0
        private const val FORECAST_STEP_4_VALUE = 50.0
        private const val FORECAST_STEP_COUNT = 4


        fun from(
            minutelyForecasts: List<MinutelyForecastModelData>,
            timeFormat: OwmTimeFormatType,
            timezoneOffset: TimezoneOffsetValue?
        ): LocationOverviewTodayOverviewPrecipitationModel {

            val forecasts = minutelyForecasts.mapNotNull { minutelyForecast ->
                minutelyForecast.precipitation?.value
            }.take(FORECAST_SIZE_LIMIT)

            val currentForecastTime = minutelyForecasts.firstOrNull()?.forecastTime
            val currentTime = currentForecastTime?.getTimeString(timezoneOffset, timeFormat)


            return LocationOverviewTodayOverviewPrecipitationModel(
                forecasts = forecasts,
                currentTime = currentTime
            )
        }

    }


}