package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import kotlin.math.min

data class ForecastOverviewPrecipitationModel(
    val headline: NBString,
    val time0: NBDateTimeDisplayModel,
    val time15: NBDateTimeDisplayModel,
    val time30: NBDateTimeDisplayModel,
    val time45: NBDateTimeDisplayModel,
    val precipitationFactors: List<Float>,
) : ForecastOverviewItem {

    override val title = NBString.ResString(R.string.screen_forecast_overview_precipitation_title)

    companion object {

        private const val MINUTELY_SIZE = 60
        private const val PRECIPITATION_FACTOR_MAX_VALUE = 10.0f

        fun from(
            oneCall: OneCallModelData
        ): ForecastOverviewPrecipitationModel? {
            return nbNullSafeList(oneCall.minutelyForecasts) { minutely ->
                val timezoneOffset = oneCall.timezoneOffset

                val minutelyForecasts = minutely.take(MINUTELY_SIZE)
                if (minutelyForecasts.size != MINUTELY_SIZE) return null

                val forecasts = minutelyForecasts.mapNotNull { minutelyForecast ->
                    minutelyForecast.precipitation?.value
                }
                val firstForecast = forecasts.firstOrNull() ?: return null
                val headline = when {
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
                            .slice(0..changeIndex)
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

                val time0 = NBDateTimeDisplayModel.from(
                    minutelyForecasts.getOrNull(0)?.forecastTime,
                    timezoneOffset
                )
                val time15 = NBDateTimeDisplayModel.from(
                    minutelyForecasts.getOrNull(15)?.forecastTime,
                    timezoneOffset
                )
                val time30 = NBDateTimeDisplayModel.from(
                    minutelyForecasts.getOrNull(30)?.forecastTime,
                    timezoneOffset
                )
                val time45 = NBDateTimeDisplayModel.from(
                    minutelyForecasts.getOrNull(45)?.forecastTime,
                    timezoneOffset
                )

                val precipitationFactors = minutelyForecasts.map { minutelyForecast ->
                    val value = minutelyForecast.precipitation?.value ?: return null
                    val precipitationFactor = value.div(PRECIPITATION_FACTOR_MAX_VALUE).toFloat()
                    min(precipitationFactor, 1f)
                }

                nbNullSafe(
                    headline,
                    time0,
                    time15,
                    time30,
                    time45
                ) { h, t0, t15, t30, t45 ->
                    ForecastOverviewPrecipitationModel(
                        headline = h,
                        time0 = t0,
                        time15 = t15,
                        time30 = t30,
                        time45 = t45,
                        precipitationFactors = precipitationFactors
                    )
                }
            }
        }

    }

}