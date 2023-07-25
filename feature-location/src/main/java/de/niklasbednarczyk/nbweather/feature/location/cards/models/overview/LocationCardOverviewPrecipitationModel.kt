package de.niklasbednarczyk.nbweather.feature.location.cards.models.overview

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.data.onecall.models.MinutelyForecastModelData
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeModel
import kotlin.math.max

data class LocationCardOverviewPrecipitationModel(
    private val forecasts: List<Double>,
    val currentDateTime: NBDateTimeModel?
) {

    val factors: List<Float>
        get() = forecasts.map { forecast ->
            val multiplier = 1.0.div(FORECAST_STEP_COUNT)

            val factor = when (forecast) {
                in FORECAST_STEP_0_VALUE..FORECAST_STEP_1_VALUE -> forecast.div(
                    FORECAST_STEP_1_VALUE
                ).times(multiplier).plus(multiplier.times(0))

                in FORECAST_STEP_1_VALUE..FORECAST_STEP_2_VALUE -> forecast.div(
                    FORECAST_STEP_2_VALUE
                ).times(multiplier).plus(multiplier.times(1))

                in FORECAST_STEP_2_VALUE..FORECAST_STEP_3_VALUE -> forecast.div(
                    FORECAST_STEP_3_VALUE
                ).times(multiplier).plus(multiplier.times(2))

                in FORECAST_STEP_3_VALUE..FORECAST_STEP_4_VALUE -> forecast.div(
                    FORECAST_STEP_4_VALUE
                ).times(multiplier).plus(multiplier.times(3))

                else -> 1.0
            }

            val value = 1 - factor.toFloat()
            max(value, 0f)
        }


    val headline: NBString?
        get() {
            val firstElement = forecasts.firstOrNull()
                ?: return NBString.Resource(R.string.screen_location_card_overview_value_precipitation_no_data)

            return when {
                forecasts.all { forecast -> forecast == 0.0 } -> {
                    NBString.Resource(R.string.screen_location_card_overview_value_precipitation_none)
                }

                forecasts.all { forecast -> forecast > 0.0 } -> {
                    NBString.Resource(R.string.screen_location_card_overview_value_precipitation_full)
                }

                firstElement == 0.0 -> {
                    val changeIndex = forecasts.indexOfFirst { forecast -> forecast > 0.0 }
                    NBString.Resource(
                        R.string.screen_location_card_overview_value_precipitation_starting,
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
                        NBString.Resource(
                            R.string.screen_location_card_overview_value_precipitation_breaking,
                            changeIndex
                        )
                    } else {
                        NBString.Resource(
                            R.string.screen_location_card_overview_value_precipitation_ending,
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
            minutelyForecasts: List<MinutelyForecastModelData>
        ): LocationCardOverviewPrecipitationModel {

            val forecasts = minutelyForecasts.mapNotNull { minutelyForecast ->
                minutelyForecast.precipitation?.value
            }.take(FORECAST_SIZE_LIMIT)

            val currentDateTime = minutelyForecasts.firstOrNull()?.forecastTime

            return LocationCardOverviewPrecipitationModel(
                forecasts = forecasts,
                currentDateTime = currentDateTime
            )
        }

    }


}