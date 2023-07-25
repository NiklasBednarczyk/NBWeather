package de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.daily

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeModel
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.settings.NBSettings
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueItem
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue
import de.niklasbednarczyk.nbweather.feature.location.extensions.icon
import de.niklasbednarczyk.nbweather.feature.location.extensions.toValueItem
import kotlin.math.abs

data class LocationOverviewDailyModel(
    val forecastTime: NBDateTimeModel?,
    val weatherIcon: NBIconModel,
    val probabilityOfPrecipitation: NBValueItem,
    private val minDailyTemperature: TemperatureValue?,
    private val maxDailyTemperature: TemperatureValue?,
    private val minTemperatureAll: TemperatureValue?,
    private val maxTemperatureAll: TemperatureValue?
) {

    val temperatures: LocationOverviewDailyTemperaturesModel?
        @Composable
        get() {
            val units = NBSettings.units

            val minDailyTemperatureRounded =
                minDailyTemperature?.getLong()?.getRoundedValue(units) ?: return null
            val maxDailyTemperatureRounded =
                maxDailyTemperature?.getLong()?.getRoundedValue(units) ?: return null

            val minTemperatureAllRounded =
                minTemperatureAll?.getLong()?.getRoundedValue(units) ?: return null
            val maxTemperatureAllRounded =
                maxTemperatureAll?.getLong()?.getRoundedValue(units) ?: return null
            val temperatureDifferenceAll =
                abs(maxTemperatureAllRounded.minus(minTemperatureAllRounded))

            return LocationOverviewDailyTemperaturesModel.from(
                minDailyTemperatureRounded = minDailyTemperatureRounded,
                minDailyTemperatureValue = minDailyTemperature,
                maxDailyTemperatureRounded = maxDailyTemperatureRounded,
                maxDailyTemperatureValue = maxDailyTemperature,
                minTemperatureAllRounded = minTemperatureAllRounded,
                maxTemperatureAllRounded = maxTemperatureAllRounded,
                temperatureDifferenceAll = temperatureDifferenceAll
            )
        }

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): List<LocationOverviewDailyModel> {

            val minTemperatureAll = oneCall.dailyForecasts.minByOrNull { daily ->
                daily.temperature?.minDailyTemperature?.getLong()?.value?.toDouble()
                    ?: Double.MAX_VALUE
            }?.temperature?.minDailyTemperature
            val maxTemperatureAll = oneCall.dailyForecasts.maxByOrNull { daily ->
                daily.temperature?.maxDailyTemperature?.getLong()?.value?.toDouble()
                    ?: Double.MIN_VALUE
            }?.temperature?.maxDailyTemperature

            return oneCall.dailyForecasts.mapNotNull { dailyForecast ->

                val weatherIcon = dailyForecast.weather?.icon?.icon

                val probabilityOfPrecipitation =
                    dailyForecast.probabilityOfPrecipitation?.toValueItem()

                nbNullSafe(
                    dailyForecast.forecastTime,
                    weatherIcon,
                    probabilityOfPrecipitation,
                ) { forecastTime, wIcon, pop ->
                    LocationOverviewDailyModel(
                        forecastTime = forecastTime,
                        weatherIcon = wIcon,
                        probabilityOfPrecipitation = pop,
                        minDailyTemperature = dailyForecast.temperature?.minDailyTemperature,
                        maxDailyTemperature = dailyForecast.temperature?.maxDailyTemperature,
                        minTemperatureAll = minTemperatureAll,
                        maxTemperatureAll = maxTemperatureAll
                    )
                }
            }
        }

    }


}