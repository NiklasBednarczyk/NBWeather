package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridModel
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridValueItem
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.list.OwmListItem
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.extensions.displayText

data class LocationOverviewTodayCurrentWeatherModel(
    override val cardTitle: OwmString?,
    val items: List<OwmListItem<OwmGridModel>>
) : LocationOverviewTodayItem {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): LocationOverviewTodayCurrentWeatherModel? {
            val cardTitle =
                OwmString.Resource(R.string.screen_location_overview_today_card_current_weather_title)

            val today = oneCall.today
            val currentWeather = oneCall.currentWeather
            val units = oneCall.metadata.units

            val items = mutableListOf<OwmListItem<OwmGridModel>>()

            owmNullSafe(today?.probabilityOfPrecipitation) { probabilityOfPrecipitation ->
                items.add(
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_probability_of_precipitation),
                            icon = OwmIcons.ProbabilityOfPrecipitation,
                            value = OwmGridValueItem.Texts(
                                probabilityOfPrecipitation.displayValue,
                                probabilityOfPrecipitation.unit
                            )
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.rain1hVolume) { rain1hVolume ->
                items.add(
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_rain),
                            icon = OwmIcons.Rain,
                            value = OwmGridValueItem.Texts(
                                rain1hVolume.displayValue,
                                rain1hVolume.unit
                            )
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.snow1hVolume) { snow1hVolume ->
                items.add(
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_snow),
                            icon = OwmIcons.Snow,
                            value = OwmGridValueItem.Texts(
                                snow1hVolume.displayValue,
                                snow1hVolume.unit
                            )
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.windSpeed) { windSpeed ->
                items.add(
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_wind_speed),
                            icon = OwmIcons.WindSpeed,
                            value = OwmGridValueItem.Texts(
                                windSpeed.displayValue,
                                windSpeed.getUnit(units)
                            )

                        )
                    )
                )
            }

            owmNullSafe(currentWeather.windGust) { windGust ->
                items.add(
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_wind_gust),
                            icon = OwmIcons.WindGust,
                            value = OwmGridValueItem.Texts(
                                windGust.displayValue,
                                windGust.getUnit(units)
                            )
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.windDegrees) { windDegrees ->
                items.add(
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_wind_direction),
                            icon = OwmIcons.WindDirection,
                            value = OwmGridValueItem.IconWithUnit(
                                icon = OwmIcons.WindDegrees,
                                rotationDegrees = windDegrees.rotationDegrees,
                                unit = windDegrees.type?.displayText ?: return@owmNullSafe null
                            )
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.pressure) { pressure ->
                items.add(
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_pressure),
                            icon = OwmIcons.Pressure,
                            value = OwmGridValueItem.Texts(
                                pressure.displayValue,
                                pressure.unit
                            )
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.humidity) { humidity ->
                items.add(
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_humidity),
                            icon = OwmIcons.Humidity,
                            value = OwmGridValueItem.Texts(
                                humidity.displayValue,
                                humidity.unit
                            )
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.dewPointTemperature) { dewPointTemperature ->
                items.add(
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_dew_point),
                            icon = OwmIcons.DewPoint,
                            value = OwmGridValueItem.Texts(
                                dewPointTemperature.displayValue,
                                dewPointTemperature.getUnit(units)
                            )
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.cloudiness) { cloudiness ->
                items.add(
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_cloudiness),
                            icon = OwmIcons.Cloudiness,
                            value = OwmGridValueItem.Texts(
                                cloudiness.displayValue,
                                cloudiness.unit
                            )
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.uvIndex) { uvIndex ->
                items.add(
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_uv_index),
                            icon = OwmIcons.UVIndex,
                            value = OwmGridValueItem.Texts(
                                uvIndex.displayValue,
                                uvIndex.unit
                            )
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.visibility) { visibility ->
                items.add(
                    OwmListItem.Full(
                        OwmGridModel(
                            title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_visibility),
                            icon = OwmIcons.Visibility,
                            value = OwmGridValueItem.Texts(
                                visibility.displayValue,
                                visibility.unit
                            )
                        )
                    )
                )
            }

            return owmNullSafeList(items) { i ->
                LocationOverviewTodayCurrentWeatherModel(
                    cardTitle = cardTitle,
                    items = i
                )
            }
        }

    }


}