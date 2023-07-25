package de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.daily

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue

data class LocationOverviewDailyTemperaturesModel(
    val minTemperature: LocationOverviewDailyTemperatureModel,
    val maxTemperature: LocationOverviewDailyTemperatureModel
) {

    val barFactor: Float
        get() = 1f.minus(maxTemperature.factor).minus(minTemperature.factor)

    companion object {

        fun from(
            minDailyTemperatureRounded: Double,
            minDailyTemperatureValue: TemperatureValue?,
            maxDailyTemperatureRounded: Double,
            maxDailyTemperatureValue: TemperatureValue?,
            minTemperatureAllRounded: Double,
            maxTemperatureAllRounded: Double,
            temperatureDifferenceAll: Double
        ): LocationOverviewDailyTemperaturesModel? {
            val minTemperatureModel = LocationOverviewDailyTemperatureModel.from(
                temperatureCurrentRounded = minDailyTemperatureRounded,
                temperatureCurrentValue = minDailyTemperatureValue,
                temperatureValueAll = minTemperatureAllRounded,
                temperatureDifferenceAll = temperatureDifferenceAll,
                prefixResId = R.string.screen_location_common_temperatures_min
            )
            val maxTemperatureModel = LocationOverviewDailyTemperatureModel.from(
                temperatureCurrentRounded = maxDailyTemperatureRounded,
                temperatureCurrentValue = maxDailyTemperatureValue,
                temperatureValueAll = maxTemperatureAllRounded,
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