package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridItem
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.extensions.displayText

data class LocationOverviewTodayCurrentWeatherModel(
    val items: List<OwmGridItem>
) : LocationOverviewTodayItem {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): LocationOverviewTodayCurrentWeatherModel? {
            val today = oneCall.today
            val currentWeather = oneCall.currentWeather
            val units = oneCall.metadata.units

            val items = mutableListOf<OwmGridItem>()

            owmNullSafe(today?.probabilityOfPrecipitation) { probabilityOfPrecipitation ->
                items.add(
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_probability_of_precipitation),
                        icon = OwmIcons.ProbabilityOfPrecipitation,
                        value = OwmGridItem.Value.Texts(
                            probabilityOfPrecipitation.displayValue,
                            probabilityOfPrecipitation.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.rain1hVolume) { rain1hVolume ->
                items.add(
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_rain),
                        icon = OwmIcons.Rain,
                        value = OwmGridItem.Value.Texts(
                            rain1hVolume.displayValue,
                            rain1hVolume.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.snow1hVolume) { snow1hVolume ->
                items.add(
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_snow),
                        icon = OwmIcons.Snow,
                        value = OwmGridItem.Value.Texts(
                            snow1hVolume.displayValue,
                            snow1hVolume.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.windSpeed) { windSpeed ->
                items.add(
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_wind_speed),
                        icon = OwmIcons.WindSpeed,
                        value = OwmGridItem.Value.Texts(
                            windSpeed.displayValue,
                            windSpeed.getUnit(units)
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.windGust) { windGust ->
                items.add(
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_wind_gust),
                        icon = OwmIcons.WindGust,
                        value = OwmGridItem.Value.Texts(
                            windGust.displayValue,
                            windGust.getUnit(units)
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.windDegrees) { windDegrees ->
                items.add(
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_wind_direction),
                        icon = OwmIcons.WindDirection,
                        value = OwmGridItem.Value.IconWithUnit(
                            icon = OwmIcons.WindDegrees,
                            rotationDegrees = windDegrees.rotationDegrees,
                            unit = windDegrees.type?.displayText ?: return@owmNullSafe null
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.pressure) { pressure ->
                items.add(
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_pressure),
                        icon = OwmIcons.Pressure,
                        value = OwmGridItem.Value.Texts(
                            pressure.displayValue,
                            pressure.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.humidity) { humidity ->
                items.add(
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_humidity),
                        icon = OwmIcons.Humidity,
                        value = OwmGridItem.Value.Texts(
                            humidity.displayValue,
                            humidity.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.dewPointTemperature) { dewPointTemperature ->
                items.add(
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_dew_point),
                        icon = OwmIcons.DewPoint,
                        value = OwmGridItem.Value.Texts(
                            dewPointTemperature.displayValue,
                            dewPointTemperature.getUnit(units)
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.cloudiness) { cloudiness ->
                items.add(
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_cloudiness),
                        icon = OwmIcons.Cloudiness,
                        value = OwmGridItem.Value.Texts(
                            cloudiness.displayValue,
                            cloudiness.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.uvIndex) { uvIndex ->
                items.add(
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_uv_index),
                        icon = OwmIcons.UVIndex,
                        value = OwmGridItem.Value.Texts(
                            uvIndex.displayValue,
                            uvIndex.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.visibility) { visibility ->
                items.add(
                    OwmGridItem.Item(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_visibility),
                        icon = OwmIcons.Visibility,
                        value = OwmGridItem.Value.Texts(
                            visibility.displayValue,
                            visibility.unit
                        )
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