package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.extensions.displayText
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.currentweather.LocationOverviewTodayCurrentWeatherItem

data class LocationOverviewTodayCurrentWeatherModel(
    val items: List<LocationOverviewTodayCurrentWeatherItem>
) : LocationOverviewTodayItem {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): LocationOverviewTodayCurrentWeatherModel? {
            val today = oneCall.dailyForecasts.firstOrNull()
            val currentWeather = oneCall.currentWeather
            val units = oneCall.metadata.units

            val items = mutableListOf<LocationOverviewTodayCurrentWeatherItem>()

            today?.probabilityOfPrecipitation?.let { probabilityOfPrecipitation ->
                items.add(
                    LocationOverviewTodayCurrentWeatherItem.Text(
                        icon = OwmIcons.ProbabilityOfPrecipitation,
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_probability_of_precipitation),
                        value = probabilityOfPrecipitation.displayValue,
                        unit = probabilityOfPrecipitation.unit
                    )
                )
            }

            currentWeather.rain1hVolume?.let { rain1hVolume ->
                items.add(
                    LocationOverviewTodayCurrentWeatherItem.Text(
                        icon = OwmIcons.Rain,
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_rain),
                        value = rain1hVolume.displayValue,
                        unit = rain1hVolume.unit
                    )
                )
            }

            currentWeather.snow1hVolume?.let { snow1hVolume ->
                items.add(
                    LocationOverviewTodayCurrentWeatherItem.Text(
                        icon = OwmIcons.Snow,
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_snow),
                        value = snow1hVolume.displayValue,
                        unit = snow1hVolume.unit
                    )
                )
            }

            currentWeather.windSpeed?.let { windSpeed ->
                items.add(
                    LocationOverviewTodayCurrentWeatherItem.Text(
                        icon = OwmIcons.WindSpeed,
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_wind_speed),
                        value = windSpeed.displayValue,
                        unit = windSpeed.getUnit(units)
                    )
                )
            }

            currentWeather.windGust?.let { windGust ->
                items.add(
                    LocationOverviewTodayCurrentWeatherItem.Text(
                        icon = OwmIcons.WindGust,
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_wind_gust),
                        value = windGust.displayValue,
                        unit = windGust.getUnit(units)
                    )
                )
            }

            currentWeather.windDegrees?.let { windDegrees ->
                items.add(
                    LocationOverviewTodayCurrentWeatherItem.Icon(
                        icon = OwmIcons.WindDirection,
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_wind_direction),
                        value = OwmIcons.WindDegrees,
                        rotationDegrees = windDegrees.rotationDegrees,
                        unit = windDegrees.type?.displayText ?: return@let null
                    )
                )
            }

            currentWeather.pressure?.let { pressure ->
                items.add(
                    LocationOverviewTodayCurrentWeatherItem.Text(
                        icon = OwmIcons.Pressure,
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_pressure),
                        value = pressure.displayValue,
                        unit = pressure.unit
                    )
                )
            }

            currentWeather.humidity?.let { humidity ->
                items.add(
                    LocationOverviewTodayCurrentWeatherItem.Text(
                        icon = OwmIcons.Humidity,
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_humidity),
                        value = humidity.displayValue,
                        unit = humidity.unit
                    )
                )
            }

            currentWeather.dewPointTemperature?.let { dewPointTemperature ->
                items.add(
                    LocationOverviewTodayCurrentWeatherItem.Text(
                        icon = OwmIcons.DewPoint,
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_dew_point),
                        value = dewPointTemperature.displayValue,
                        unit = dewPointTemperature.getUnit(units)
                    )
                )
            }

            currentWeather.cloudiness?.let { cloudiness ->
                items.add(
                    LocationOverviewTodayCurrentWeatherItem.Text(
                        icon = OwmIcons.Cloudiness,
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_cloudiness),
                        value = cloudiness.displayValue,
                        unit = cloudiness.unit
                    )
                )
            }

            currentWeather.uvIndex?.let { uvIndex ->
                items.add(
                    LocationOverviewTodayCurrentWeatherItem.Text(
                        icon = OwmIcons.UVIndex,
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_uv_index),
                        value = uvIndex.displayValue,
                        unit = uvIndex.unit
                    )
                )
            }

            currentWeather.visibility?.let { visibility ->
                items.add(
                    LocationOverviewTodayCurrentWeatherItem.Text(
                        icon = OwmIcons.Visibility,
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_visibility),
                        value = visibility.displayValue,
                        unit = visibility.unit
                    )
                )
            }

            return owmNullSafeList(items) { i ->
                LocationOverviewTodayCurrentWeatherModel(
                    items = i
                )
            }
        }

    }


}