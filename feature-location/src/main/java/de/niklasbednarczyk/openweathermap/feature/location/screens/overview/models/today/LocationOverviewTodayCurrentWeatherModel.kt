package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridIconModel.Companion.toGridIcon
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridItem
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridValueItem
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.openweathermap.feature.location.extensions.displayText

data class LocationOverviewTodayCurrentWeatherModel(
    override val cardTitle: OwmString?,
    val items: List<OwmGridItem>
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

            val items = mutableListOf<OwmGridItem>()

            owmNullSafe(today?.probabilityOfPrecipitation) { probabilityOfPrecipitation ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_probability_of_precipitation),
                        gridIcon = OwmIcons.ProbabilityOfPrecipitation.toGridIcon(),
                        value = OwmGridValueItem.Texts(
                            probabilityOfPrecipitation.displayValue,
                            probabilityOfPrecipitation.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.rain1hVolume) { rain1hVolume ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_rain),
                        gridIcon = OwmIcons.Rain.toGridIcon(),
                        value = OwmGridValueItem.Texts(
                            rain1hVolume.displayValue,
                            rain1hVolume.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.snow1hVolume) { snow1hVolume ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_snow),
                        gridIcon = OwmIcons.Snow.toGridIcon(),
                        value = OwmGridValueItem.Texts(
                            snow1hVolume.displayValue,
                            snow1hVolume.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.windSpeed) { windSpeed ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_wind_speed),
                        gridIcon = OwmIcons.WindSpeed.toGridIcon(),
                        value = OwmGridValueItem.Texts(
                            windSpeed.displayValue,
                            windSpeed.getUnit(units)
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.windGust) { windGust ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_wind_gust),
                        gridIcon = OwmIcons.WindGust.toGridIcon(),
                        value = OwmGridValueItem.Texts(
                            windGust.displayValue,
                            windGust.getUnit(units)
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.windDegrees) { windDegrees ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_wind_direction),
                        gridIcon = OwmIcons.WindDegrees.toGridIcon(windDegrees.rotationDegrees),
                        value = OwmGridValueItem.Texts(
                            windDegrees.type?.displayText ?: return@owmNullSafe null
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.pressure) { pressure ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_pressure),
                        gridIcon = OwmIcons.Pressure.toGridIcon(),
                        value = OwmGridValueItem.Texts(
                            pressure.displayValue,
                            pressure.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.humidity) { humidity ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_humidity),
                        gridIcon = OwmIcons.Humidity.toGridIcon(),
                        value = OwmGridValueItem.Texts(
                            humidity.displayValue,
                            humidity.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.dewPointTemperature) { dewPointTemperature ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_dew_point),
                        gridIcon = OwmIcons.DewPoint.toGridIcon(),
                        value = OwmGridValueItem.Texts(
                            dewPointTemperature.displayValue,
                            dewPointTemperature.getUnit(units)
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.cloudiness) { cloudiness ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_cloudiness),
                        gridIcon = OwmIcons.Cloudiness.toGridIcon(),
                        value = OwmGridValueItem.Texts(
                            cloudiness.displayValue,
                            cloudiness.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.uvIndex) { uvIndex ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_uv_index),
                        gridIcon = OwmIcons.UVIndex.toGridIcon(),
                        value = OwmGridValueItem.Texts(
                            uvIndex.displayValue,
                            uvIndex.unit
                        )
                    )
                )
            }

            owmNullSafe(currentWeather.visibility) { visibility ->
                items.add(
                    OwmGridItem.ThreeLines(
                        title = OwmString.Resource(R.string.screen_location_overview_today_current_weather_title_visibility),
                        gridIcon = OwmIcons.Visibility.toGridIcon(),
                        value = OwmGridValueItem.Texts(
                            visibility.displayValue,
                            visibility.unit
                        )
                    )
                )
            }

            return owmNullSafeList(
                items
            ) { i ->
                LocationOverviewTodayCurrentWeatherModel(
                    cardTitle = cardTitle,
                    items = i
                )
            }
        }

    }


}