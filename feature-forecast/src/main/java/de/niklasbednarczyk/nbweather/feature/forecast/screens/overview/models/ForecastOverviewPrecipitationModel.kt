package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.data.onecall.models.MinutelyForecastModelData
import de.niklasbednarczyk.nbweather.feature.forecast.constants.ForecastUnitsLimits

data class ForecastOverviewPrecipitationModel(
    val headline: NBString,
    val time0: NBDateTimeDisplayModel,
    val time15: NBDateTimeDisplayModel,
    val time30: NBDateTimeDisplayModel,
    val time45: NBDateTimeDisplayModel,
    val precipitationFactors: List<Float>,
) : ForecastOverviewItem {

    companion object {

        private const val MINUTELY_SIZE = 60

        private fun List<MinutelyForecastModelData>.getHeadline(): NBString? {
            val forecasts = mapNotNull { minutelyForecast ->
                minutelyForecast.precipitation?.value
            }
            val firstForecast = forecasts.firstOrNull() ?: return null
            return when {
                forecasts.all { forecast -> forecast == 0.0 } -> {
                    NBString.ResString(R.string.screen_forecast_overview_precipitation_headline_none)
                }

                forecasts.all { forecast -> forecast > 0.0 } -> {
                    NBString.ResString(R.string.screen_forecast_overview_precipitation_headline_full)
                }

                firstForecast == 0.0 -> {
                    val changeIndex = forecasts.indexOfFirst { forecast -> forecast > 0.0 }
                    NBString.ResPlurals(
                        R.plurals.screen_forecast_overview_precipitation_headline_starting,
                        changeIndex,
                        changeIndex
                    )
                }

                firstForecast > 0.0 -> {
                    val changeIndex = forecasts.indexOfFirst { forecast -> forecast == 0.0 }

                    val isBreaking = forecasts
                        .slice(changeIndex..forecasts.lastIndex)
                        .any { forecast -> forecast > 0.0 }

                    if (isBreaking) {
                        NBString.ResPlurals(
                            R.plurals.screen_forecast_overview_precipitation_headline_breaking,
                            changeIndex,
                            changeIndex
                        )
                    } else {
                        NBString.ResPlurals(
                            R.plurals.screen_forecast_overview_precipitation_headline_ending,
                            changeIndex,
                            changeIndex
                        )
                    }
                }

                else -> null
            }
        }

        private fun List<MinutelyForecastModelData>.getTime(
            index: Int,
            timezoneOffset: NBTimezoneOffsetValue?
        ): NBDateTimeDisplayModel? {
            return NBDateTimeDisplayModel.from(
                getOrNull(index)?.forecastTime,
                timezoneOffset
            )
        }

        private fun List<MinutelyForecastModelData>.getPrecipitationFactors(): List<Float>? {
            val maxValue = ForecastUnitsLimits.Precipitation.max.value
            return map { minutelyForecast ->
                val value = minutelyForecast.precipitation?.value ?: return null
                val precipitationFactor = (value / maxValue).toFloat()
                precipitationFactor.coerceIn(0f, 1f)
            }
        }

        fun from(
            timezoneOffset: NBTimezoneOffsetValue?,
            minutelyForecasts: List<MinutelyForecastModelData>
        ): ForecastOverviewPrecipitationModel? {
            return nbNullSafeList(minutelyForecasts) { forecasts ->
                val minutely = forecasts.take(MINUTELY_SIZE)
                if (minutely.size != MINUTELY_SIZE) return null

                nbNullSafe(
                    minutely.getHeadline(),
                    minutely.getTime(0, timezoneOffset),
                    minutely.getTime(15, timezoneOffset),
                    minutely.getTime(30, timezoneOffset),
                    minutely.getTime(45, timezoneOffset),
                    minutely.getPrecipitationFactors()
                ) { headline, time0, time15, time30, time45, precipitationFactors ->
                    ForecastOverviewPrecipitationModel(
                        headline = headline,
                        time0 = time0,
                        time15 = time15,
                        time30 = time30,
                        time45 = time45,
                        precipitationFactors = precipitationFactors
                    )
                }
            }
        }

    }

}