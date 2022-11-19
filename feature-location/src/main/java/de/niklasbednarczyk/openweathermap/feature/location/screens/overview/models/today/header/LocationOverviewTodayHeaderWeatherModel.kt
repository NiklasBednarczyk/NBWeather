package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.header

import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.data.onecall.models.CurrentWeatherModelData
import de.niklasbednarczyk.openweathermap.feature.location.extensions.icon

data class LocationOverviewTodayHeaderWeatherModel(
    val currentTemperature: OwmString?,
    val feelsLikeTemperature: OwmString,
    val feelsLikePrefix: OwmString,
    val temperatureUnit: OwmString,
    val weatherDescription: OwmString,
    val weatherIcon: OwmIconModel
) {

    companion object {

        fun from(
            currentWeather: CurrentWeatherModelData,
            units: OwmUnitsType
        ): LocationOverviewTodayHeaderWeatherModel? {
            val currentTemperatureData = currentWeather.currentTemperature
            val feelsLikeTemperatureData = currentWeather.feelsLikeTemperature
            val weatherDescriptionData = currentWeather.weather?.description
            val weatherIconData = currentWeather.weather?.icon

            val feelsLikePrefix = OwmString.Resource(R.string.value_temperature_feels_like_prefix)

            return owmNullSafe(
                currentTemperatureData,
                feelsLikeTemperatureData,
                weatherDescriptionData,
                weatherIconData
            ) { currentTemperature, feelsLikeTemperature, weatherDescription, weatherIcon ->

                LocationOverviewTodayHeaderWeatherModel(
                    currentTemperature = currentTemperature.displayValue,
                    feelsLikeTemperature = feelsLikeTemperature.displayValueWithShortUnit,
                    feelsLikePrefix = feelsLikePrefix,
                    temperatureUnit = currentTemperature.getUnit(units),
                    weatherDescription = weatherDescription.value,
                    weatherIcon = weatherIcon.type.icon
                )
            }

        }

    }


}