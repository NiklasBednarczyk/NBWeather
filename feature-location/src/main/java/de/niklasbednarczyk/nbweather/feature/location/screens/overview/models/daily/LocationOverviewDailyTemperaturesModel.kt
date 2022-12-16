package de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.daily

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.nbweather.core.ui.R

data class LocationOverviewDailyTemperaturesModel(
    val minTemperature: LocationOverviewDailyTemperatureModel,
    val maxTemperature: LocationOverviewDailyTemperatureModel
) {

    val barFactor: Float
        get() = 1f.minus(maxTemperature.factor).minus(minTemperature.factor)

    companion object {

        private const val MIN_WEIGHT = 0.00001f

        fun from(
            dailyForecast: DailyForecastModelData,
            minTemperatureAll: Double,
            maxTemperatureAll: Double,
            temperatureDifferenceAll: Double
        ): LocationOverviewDailyTemperaturesModel? {
            val minTemperatureCurrent = dailyForecast.temperature?.minDailyTemperature
            val maxTemperatureCurrent = dailyForecast.temperature?.maxDailyTemperature

            val minTemperatureModel = LocationOverviewDailyTemperatureModel.from(
                temperatureCurrent = minTemperatureCurrent,
                temperatureValueAll = minTemperatureAll,
                temperatureDifferenceAll = temperatureDifferenceAll,
                prefixResId = R.string.screen_location_common_temperatures_min
            )
            val maxTemperatureModel = LocationOverviewDailyTemperatureModel.from(
                temperatureCurrent = maxTemperatureCurrent,
                temperatureValueAll = maxTemperatureAll,
                temperatureDifferenceAll = temperatureDifferenceAll,
                prefixResId = R.string.screen_location_common_temperatures_max
            )

            return nbNullSafe(
                minTemperatureModel,
                maxTemperatureModel,
            ) { minTemp, maxTemp ->
                LocationOverviewDailyTemperaturesModel(
                    minTemperature = minTemp,
                    maxTemperature = maxTemp
                )
            }
        }
    }

}